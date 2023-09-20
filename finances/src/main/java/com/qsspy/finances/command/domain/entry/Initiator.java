package com.qsspy.finances.command.domain.entry;

import com.qsspy.commons.architecture.cqrs.DomainValidationException;
import com.qsspy.commons.architecture.ddd.ValueObject;

record Initiator(String email) implements ValueObject {
    @Override
    public void validate() {
        if(email == null || email.isBlank()) {
            throw new DomainValidationException("Initiator Email cannot be blank!");
        }
    }
}
