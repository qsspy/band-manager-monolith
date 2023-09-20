package com.qsspy.commons.architecture.cqrs;

public interface Command {

    /**
     * Self validation method
     */
    void validate();
}
