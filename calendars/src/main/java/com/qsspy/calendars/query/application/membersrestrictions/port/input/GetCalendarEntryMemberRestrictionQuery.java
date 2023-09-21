package com.qsspy.calendars.query.application.membersrestrictions.port.input;

import com.qsspy.commons.architecture.cqrs.Query;
import com.qsspy.commons.architecture.cqrs.QueryValidationException;

import java.util.UUID;

public record GetCalendarEntryMemberRestrictionQuery(
       UUID bandId
) implements Query {
    @Override
    public void validate() {
        if(bandId == null) {
            throw new QueryValidationException("Band Id cannot be null");
        }
    }
}
