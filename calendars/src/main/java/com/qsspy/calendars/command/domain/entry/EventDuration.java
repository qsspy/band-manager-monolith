package com.qsspy.calendars.command.domain.entry;

import com.qsspy.commons.architecture.ddd.DomainValidationException;
import com.qsspy.commons.architecture.ddd.ValueObject;

import java.time.Duration;

record EventDuration(
        Duration value
) implements ValueObject {
    @Override
    public void validate() {
        if(value == null) {
            throw new DomainValidationException("Event duration cannot be null!");
        }
    }
}
