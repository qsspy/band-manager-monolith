package com.qsspy.authservice.application.authorizer;

import com.qsspy.authservice.application.authorizer.port.input.AuthInterceptor;
import com.qsspy.authservice.application.authorizer.port.input.UserContext;
import com.qsspy.authservice.application.authorizer.port.input.UserUnauthorizedException;
import com.qsspy.authservice.application.authorizer.port.output.UserContextRepository;
import com.qsspy.authservice.application.common.CustomJwtClaim;
import com.qsspy.authservice.application.common.port.output.TokenGenerationSecretProvider;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.UUID;
import java.util.function.Function;
import java.util.function.Supplier;

@Service
@RequiredArgsConstructor
class JwtAuthInterceptor implements AuthInterceptor {

    private final TokenGenerationSecretProvider secretProvider;
    private final UserContextRepository userContextRepository;

    @Override
    public <T> T withAuthorization(final String authToken, Function<UserContext, T> action, final Supplier<T> userNotAuthenticatedSupplier) {
        final var normalizedToken = normalizeToken(authToken);

        if(!isTokenValid(authToken)) {
            return userNotAuthenticatedSupplier.get();
        }

        final var userId = getClaimFromToken(authToken, claims -> claims.get(CustomJwtClaim.USER_ID, UUID.class));
        return userContextRepository
                .findById(userId)
                .map(action)
                .orElseThrow(() -> new IllegalStateException("Could not find user with this id!"));
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
        return expiration.before(new Date());
    }

    private String normalizeToken(final String authToken) {
        if(authToken.startsWith("Bearer ")) {
            return authToken.replace("Bearer ", "");
        }
        return authToken;
    }
}
