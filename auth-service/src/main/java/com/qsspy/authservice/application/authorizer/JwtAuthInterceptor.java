package com.qsspy.authservice.application.authorizer;

import com.qsspy.authservice.application.authorizer.port.input.AuthInterceptor;
import com.qsspy.authservice.application.authorizer.port.input.UserContext;
import com.qsspy.authservice.application.authorizer.port.output.UserContextRepository;
import com.qsspy.authservice.application.common.CustomJwtClaim;
import com.qsspy.authservice.application.common.port.output.TokenGenerationSecretProvider;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.UUID;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

@Service
@RequiredArgsConstructor
@Slf4j
class JwtAuthInterceptor implements AuthInterceptor {

    private final TokenGenerationSecretProvider secretProvider;
    private final UserContextRepository userContextRepository;


    @Override
    public <T> T withUserWithoutBandAuthorization(final String authToken, final Function<UserContext, T> action, final Supplier<T> userNotAuthorizedSupplier) {
        return withRestrictedAuthorization(
                authToken, action, userNotAuthorizedSupplier,
                context -> context.userMemberBandId() == null && context.userOwnBandId() == null
        );
    }

    @Override
    public <T> T withBandAdminAuthorization(final String authToken, final UUID bandId, final Function<UserContext, T> action, final Supplier<T> userNotAuthorizedSupplier) {
        return withRestrictedAuthorization(
                authToken, action, userNotAuthorizedSupplier,
                context -> bandId.equals(context.userOwnBandId())
        );
    }

    @Override
    public <T> T withBandMemberAuthorization(final String authToken, final UUID bandId, final Function<UserContext, T> action, final Supplier<T> userNotAuthorizedSupplier) {
        return withRestrictedAuthorization(
                authToken, action, userNotAuthorizedSupplier,
                context -> bandId.equals(context.userMemberBandId()) || bandId.equals(context.userOwnBandId())
        );
    }

    @Override
    public <T> T withBandMemberPrivilegeRestrictedAuthorization(final String authToken, final UUID bandId, final Predicate<UserContext.Privileges> privilegesRestriction, final Function<UserContext, T> action, final Supplier<T> userNotAuthorizedSupplier) {
        return withRestrictedAuthorization(
                authToken, action, userNotAuthorizedSupplier,
                context -> (bandId.equals(context.userMemberBandId()) || bandId.equals(context.userOwnBandId())) && privilegesRestriction.test(context.bandPrivileges())
        );
    }

    private <T> T withRestrictedAuthorization(
            final String authToken,
            final Function<UserContext, T> action,
            final Supplier<T> userNotAuthorizedSupplier,
            final Predicate<UserContext> restrictionPredicate
    ) {
        final var normalizedToken = normalizeToken(authToken);

        if(!isTokenValid(normalizedToken)) {
            log.error("Auth token was not valid!");
            return userNotAuthorizedSupplier.get();
        }

        final var userId = getClaimFromToken(normalizedToken, claims -> claims.get(CustomJwtClaim.USER_ID, String.class));
        return userContextRepository
                .findById(UUID.fromString(userId))
                .map(context -> {
                    log.info("Trying to authenticate user with context: {}", context);
                    log.info("Predicate result : {}", restrictionPredicate.test(context));
                    return context;
                })
                .filter(restrictionPredicate)
                .map(action)
                .orElseThrow(() -> new IllegalStateException("Could not find user with this id or required authentication!"));
    }

    private <T> T getClaimFromToken(final String token, final Function<Claims, T> claimsResolver) {
        final var claims = getAllClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }

    private Claims getAllClaimsFromToken(final String token) {
        final var key = Keys.hmacShaKeyFor(secretProvider.getSecret().getBytes(StandardCharsets.UTF_8));
        return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody();
    }

    private boolean isTokenValid(final String token) {
        final var expiration = getClaimFromToken(token, Claims::getExpiration);
        return expiration.after(new Date());
    }

    private String normalizeToken(final String authToken) {
        if(authToken.startsWith("Bearer ")) {
            return authToken.replace("Bearer ", "");
        }
        return authToken;
    }
}
