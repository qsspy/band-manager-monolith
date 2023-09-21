package com.qsspy.finances.command.domain.entry;

import com.qsspy.commons.architecture.ddd.DomainValidationException;
import com.qsspy.commons.architecture.ddd.ValueObject;

record Description(String text) implements ValueObject {
    @Override
    public void validate() {
        if(text == null || text.isBlank()) {
            throw new DomainValidationException("Description text cannot be blank!");
        }
    }
}
