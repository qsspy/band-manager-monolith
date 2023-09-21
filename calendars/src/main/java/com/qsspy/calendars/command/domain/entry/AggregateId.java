package com.qsspy.calendars.command.domain.entry;

import com.qsspy.commons.architecture.cqrs.DomainValidationException;
import com.qsspy.commons.architecture.ddd.ValueObject;

import java.util.UUID;

record AggregateId(UUID value) implements ValueObject {
    @Override
    public void validate() {
        if(value == null) {
            throw new DomainValidationException("Id cannot be null!");
        }
    }
}
