package com.qsspy.calendars.command.domain.entry;

import com.qsspy.commons.architecture.ddd.DomainValidationException;
import com.qsspy.commons.architecture.ddd.ValueObject;

import java.math.BigDecimal;

record Amount(BigDecimal value) implements ValueObject {
    @Override
    public void validate() {
        if(value == null) {
            throw new DomainValidationException("Amount value cannot be null");
        }
    }
}
