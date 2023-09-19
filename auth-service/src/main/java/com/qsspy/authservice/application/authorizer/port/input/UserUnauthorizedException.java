package com.qsspy.authservice.application.authorizer.port.input;

public class UserUnauthorizedException extends RuntimeException {

    public UserUnauthorizedException() {
        super("User not authorized!");
    }
}
