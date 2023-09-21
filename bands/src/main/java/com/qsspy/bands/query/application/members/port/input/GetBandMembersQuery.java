package com.qsspy.bands.query.application.members.port.input;

import com.qsspy.commons.architecture.cqrs.Query;
import com.qsspy.commons.architecture.cqrs.QueryValidationException;

import java.util.UUID;

public record GetBandMembersQuery(
        UUID bandId
) implements Query {
    @Override
    public void validate() {
        if(bandId == null) {
            throw new QueryValidationException("Band id cannot be null");
        }
    }
}
