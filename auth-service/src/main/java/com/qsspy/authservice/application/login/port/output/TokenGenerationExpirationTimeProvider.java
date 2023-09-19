package com.qsspy.authservice.application.login.port.output;

public interface TokenGenerationExpirationTimeProvider {
    /**
     * Provides count of token validation minutes
     *
     * @return validation minutes
     */
    int getTokenValidationMinutes();
}
