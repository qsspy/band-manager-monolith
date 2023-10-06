package com.qsspy.authservice.application.login.port.output;

import java.util.Optional;
import java.util.UUID;

public interface UserLoginRepository {

    /**
     * Checks if user with given email and password exists in the repository
     *
     * @param email user email
     * @param password user password
     * @return check result
     */
    Optional<UserLoginDTO> getUserLoginDataByCredentials(final String email, final String password);
}