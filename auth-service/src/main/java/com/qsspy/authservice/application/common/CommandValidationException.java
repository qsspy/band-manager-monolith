package com.qsspy.authservice.application.common;

public class CommandValidationException extends RuntimeException {

    public CommandValidationException(final String message) {
        super(message);
    }
}
