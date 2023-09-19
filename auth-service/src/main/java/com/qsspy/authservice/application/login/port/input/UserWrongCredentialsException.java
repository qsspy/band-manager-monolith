package com.qsspy.authservice.application.login.port.input;

public class UserWrongCredentialsException extends RuntimeException {
    public UserWrongCredentialsException() {
        super("Credentials were not correct!");
    }
}
