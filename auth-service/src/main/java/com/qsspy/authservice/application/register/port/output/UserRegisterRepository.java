package com.qsspy.authservice.application.register.port.output;

import com.qsspy.authservice.application.register.port.input.RegisterCommand;

public interface UserRegisterRepository {

    /**
     * Checks if user with given email exists in the repository
     *
     * @param email user email
     * @return flag indicating if user with given email already exists
     */
    boolean existsByEmail(final String email);

    /**
     * Saves the new user in the repository using data provided in command
     *
     * @param command command containing user data
     */
    void save(final RegisterCommand command);
}
