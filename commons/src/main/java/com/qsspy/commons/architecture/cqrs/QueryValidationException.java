package com.qsspy.commons.architecture.cqrs;

public class QueryValidationException extends RuntimeException {

    public QueryValidationException(final String message) {
        super(message);
    }
}
