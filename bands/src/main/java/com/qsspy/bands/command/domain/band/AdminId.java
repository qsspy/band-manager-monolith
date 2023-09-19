package com.qsspy.bands.command.domain.band;

import com.qsspy.commons.architecture.DomainValidationException;
import com.qsspy.commons.architecture.ValueObject;

import java.util.UUID;

record AdminId(UUID value) implements ValueObject {
    @Override
    public void validate() {
        if(value == null) {
            throw new DomainValidationException("Id cannot be null!");
        }
    }
}
