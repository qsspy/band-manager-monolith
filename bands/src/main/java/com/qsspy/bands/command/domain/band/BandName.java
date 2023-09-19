package com.qsspy.bands.command.domain.band;

import com.qsspy.commons.architecture.DomainValidationException;
import com.qsspy.commons.architecture.ValueObject;

import java.util.UUID;

record BandName(String value) implements ValueObject {
    @Override
    public void validate() {
        if(value == null || value.isBlank()) {
            throw new DomainValidationException("Name cannot be blank!");
        }
    }
}
