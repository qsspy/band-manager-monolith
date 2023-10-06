package com.qsspy.authservice.application.login;

import com.qsspy.authservice.application.common.CustomJwtClaim;
import com.qsspy.authservice.application.common.port.output.TokenGenerationSecretProvider;
import com.qsspy.authservice.application.login.port.input.LoginService;
import com.qsspy.authservice.application.login.port.input.UserWrongCredentialsException;
import com.qsspy.authservice.application.login.port.output.TokenGenerationExpirationTimeProvider;
import com.qsspy.authservice.application.login.port.output.UserLoginDTO;
import com.qsspy.authservice.application.login.port.output.UserLoginRepository;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.UUID;

@Component
@RequiredArgsConstructor
class JwtLoginService implements LoginService {

    private final UserLoginRepository userLoginRepository;
    private final TokenGenerationSecretProvider tokenGenerationPropertiesProvider;
    private final TokenGenerationExpirationTimeProvider tokenGenerationExpirationTimeProvider;

    @Override
    public String login(final String email, final String password) {
        return userLoginRepository
                .getUserLoginDataByCredentials(email, password)
                .map(loginData -> generateJwtToken(email, loginData))
                .orElseThrow(UserWrongCredentialsException::new);
    }

    private String generateJwtToken(final String email, final UserLoginDTO loginData) {

        final var currentDateTimestamp = System.currentTimeMillis();
        final var key = Keys.hmacShaKeyFor(tokenGenerationPropertiesProvider.getSecret().getBytes(StandardCharsets.UTF_8));
        return Jwts
                .builder()
                .setSubject(email)
                .claim(CustomJwtClaim.USER_ID, loginData.userId().toString())
                .claim(CustomJwtClaim.BAND_ID, loginData.getBandMembershipUuid() != null ? loginData.getBandMembershipUuid().toString() : null)
                .setIssuedAt(new Date(currentDateTimestamp))
                .setExpiration(new Date(currentDateTimestamp + expirationTimeAsMillis()))
                .signWith(key, SignatureAlgorithm.HS512).compact();
    }

    private long expirationTimeAsMillis() {
        return (long) tokenGenerationExpirationTimeProvider.getTokenValidationMinutes() * 60 * 1000;
    }
}
