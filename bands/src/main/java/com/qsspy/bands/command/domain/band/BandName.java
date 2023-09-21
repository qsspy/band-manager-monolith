package com.qsspy.bands.command.domain.band;

import com.qsspy.commons.architecture.ddd.DomainValidationException;
import com.qsspy.commons.architecture.ddd.ValueObject;

record BandName(String value) implements ValueObject {
    @Override
    public void validate() {
        if(value == null || value.isBlank()) {
            throw new DomainValidationException("Name cannot be blank!");
        }
    }
}
