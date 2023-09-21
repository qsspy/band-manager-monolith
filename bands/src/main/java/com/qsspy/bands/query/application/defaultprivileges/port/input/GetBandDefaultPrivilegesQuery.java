package com.qsspy.bands.query.application.defaultprivileges.port.input;

import com.qsspy.commons.architecture.cqrs.Query;
import com.qsspy.commons.architecture.cqrs.QueryValidationException;

import java.util.UUID;

public record GetBandDefaultPrivilegesQuery(
        UUID bandId
) implements Query {
    @Override
    public void validate() {
        if(bandId == null) {
            throw new QueryValidationException("Band id cannot be null");
        }
    }
}
