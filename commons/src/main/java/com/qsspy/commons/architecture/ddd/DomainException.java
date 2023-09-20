package com.qsspy.commons.architecture.ddd;

public class DomainException extends RuntimeException {

    public DomainException(final String message) {
        super(message);
    }
}
