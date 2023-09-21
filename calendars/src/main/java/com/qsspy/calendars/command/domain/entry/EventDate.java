package com.qsspy.calendars.command.domain.entry;

import com.qsspy.commons.architecture.cqrs.DomainValidationException;
import com.qsspy.commons.architecture.ddd.ValueObject;

import java.time.LocalDateTime;
import java.util.UUID;

record EventDate(LocalDateTime value) implements ValueObject {
    @Override
    public void validate() {
        if(value == null) {
            throw new DomainValidationException("Event date cannot be null!");
        }
    }
}
