package com.qsspy.authservice.infrastructure.port.configuration;


import com.qsspy.authservice.application.common.port.output.TokenGenerationSecretProvider;
import com.qsspy.authservice.application.login.port.output.TokenGenerationExpirationTimeProvider;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "jwt")
class ConfigurableJwtTokenGenerationPropertiesProvider implements TokenGenerationSecretProvider, TokenGenerationExpirationTimeProvider {

    private String secret;
    private Expiration expiration;

    @Override
    public String getSecret() {
        return secret;
    }

    @Override
    public int getTokenValidationMinutes() {
        return expiration.minutes();
    }

    record Expiration(int minutes) { }
}