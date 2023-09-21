package com.qsspy.commons.architecture.cqrs;

public interface Query {

    /**
     * Self validation method
     */
    void validate();
}
