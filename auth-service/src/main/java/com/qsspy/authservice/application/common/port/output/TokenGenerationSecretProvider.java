package com.qsspy.authservice.application.common.port.output;

public interface TokenGenerationSecretProvider {

    /**
     * Provides token for secret generation
     *
     * @return token secret
     */
    String getSecret();
}
