package com.qsspy.authservice.application.authorizer.port.input;

import java.util.function.Function;
import java.util.function.Supplier;

public interface AuthInterceptor {

    /**
     * Checks if user described by given token is authenticated. If yes then authorization data is returned and can be used in provided callback
     *
     * @param authToken authorization token
     * @param action action to be executed if authorization is successful
     * @param userNotAuthenticatedSupplier result to be returned of user was not authenticated
     * @return return value of callback
     */
    <T> T withAuthorization(final String authToken, final Function<UserContext, T> action, final Supplier<T> userNotAuthenticatedSupplier);
}
