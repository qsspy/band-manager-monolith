package com.qsspy.commons.architecture.cqrs;

public class CommandValidationException extends RuntimeException {

    public CommandValidationException(final String message) {
        super(message);
    }
}
