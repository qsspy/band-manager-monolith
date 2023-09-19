package com.qsspy.authservice.application.login.port.input;

public interface LoginService {

    /**
     * Login user using his email and password
     *
     * @param email email of the user
     * @param password password of the user
     * @return authentication token
     */
    String login(final String email, final String password);
}
