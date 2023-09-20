package com.qsspy.authservice.application.authorizer.port.input;

import java.util.UUID;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

public interface AuthInterceptor {

    /**
     * Checks if user described by given token is authenticated and is not assigned to any band. If yes then authorization data is returned and can be used in provided callback
     *
     * @param authToken authorization token
     * @param action action to be executed if authorization is successful
     * @param userNotAuthorizedSupplier result to be returned of user was not authorized
     * @return return value of callback
     */
    <T> T withUserWithoutBandAuthorization(final String authToken, final Function<UserContext, T> action, final Supplier<T> userNotAuthorizedSupplier);

    /**
     * Checks if user described by given token is authenticated and authorized as band admin. If yes then authorization data is returned and can be used in provided callback
     *
     * @param authToken authorization token
     * @param bandId band identifier
     * @param action action to be executed if authorization is successful
     * @param userNotAuthorizedSupplier result to be returned of user was not authorized
     * @return return value of callback
     */
    <T> T withBandAdminAuthorization(final String authToken, final UUID bandId, final Function<UserContext, T> action, final Supplier<T> userNotAuthorizedSupplier);

    /**
     * Checks if user described by given token is authenticated and authorized as band member (not admin). If yes then authorization data is returned and can be used in provided callback
     *
     * @param authToken authorization token
     * @param action action to be executed if authorization is successful
     * @param bandId band identifier
     * @param userNotAuthorizedSupplier result to be returned of user was not authorized
     * @return return value of callback
     */
    <T> T withBandMemberAuthorization(final String authToken, final UUID bandId, final Function<UserContext, T> action, final Supplier<T> userNotAuthorizedSupplier);

    /**
     * Checks if user described by given token is authenticated and authorized as band member (not admin). If yes then authorization data is returned and can be used in provided callback
     *
     * @param authToken authorization token
     * @param action action to be executed if authorization is successful
     * @param bandId band identifier
     * @param privilegesRestriction restriction on user privileges
     * @param userNotAuthorizedSupplier result to be returned of user was not authorized
     * @return return value of callback
     */
    <T> T withBandMemberPrivilegeRestrictedAuthorization(final String authToken, final UUID bandId, final Predicate<UserContext.Privileges> privilegesRestriction, final Function<UserContext, T> action, final Supplier<T> userNotAuthorizedSupplier);
}
