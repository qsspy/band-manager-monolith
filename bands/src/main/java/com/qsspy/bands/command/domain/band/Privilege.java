package com.qsspy.bands.command.domain.band;

import com.qsspy.commons.architecture.cqrs.DomainValidationException;
import com.qsspy.commons.architecture.ddd.ValueObject;

import java.util.UUID;

record Privilege(boolean isAllowed) implements ValueObject {

    private static final Privilege ALLOWED = new Privilege(true);
    private static final Privilege FORBIDDEN = new Privilege(false);

    static Privilege allowed() {
        return ALLOWED;
    }

    static Privilege forbidden() {
        return FORBIDDEN;
    }

    static Privilege from(final boolean isAllowed) {
        return isAllowed ? allowed() : forbidden();
    }

    @Override
    public void validate() {
        //No need for validation
    }
}
