package com.qsspy.finances.command.domain.entry;

import com.qsspy.commons.architecture.ddd.DomainValidationException;
import com.qsspy.commons.architecture.ddd.ValueObject;

import java.util.UUID;

record BandId(UUID value) implements ValueObject {
    @Override
    public void validate() {
        if(value == null) {
            throw new DomainValidationException("Id cannot be null!");
        }
    }
}
