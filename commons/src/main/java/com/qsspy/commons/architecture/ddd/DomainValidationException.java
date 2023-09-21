package com.qsspy.commons.architecture.ddd;

public class DomainValidationException extends RuntimeException {

    public DomainValidationException(final String message) {
        super(message);
    }
}
