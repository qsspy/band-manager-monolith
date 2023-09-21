package com.qsspy.calendars.command.domain.entry;

import com.qsspy.commons.architecture.cqrs.DomainValidationException;
import com.qsspy.commons.architecture.ddd.ValueObject;

import java.math.BigDecimal;

record Address(String fullAddress) implements ValueObject {
    @Override
    public void validate() {
        if(fullAddress == null || fullAddress.isBlank()) {
            throw new DomainValidationException("Full address cannot be blank");
        }
    }
}
