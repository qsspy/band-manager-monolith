package com.qsspy.bands.query.application.userprivileges.port.input;

import com.qsspy.commons.architecture.cqrs.Query;
import com.qsspy.commons.architecture.cqrs.QueryValidationException;

import java.util.UUID;

public record GetUserBandPrivilegesQuery(
        UUID bandId,
        UUID memberId
) implements Query {

    @Override
    public void validate() {
        if(bandId == null) {
            throw new QueryValidationException("Band id cannot be null");
        }
        if(memberId == null) {
            throw new QueryValidationException("Member id cannot be null");
        }
    }
}
