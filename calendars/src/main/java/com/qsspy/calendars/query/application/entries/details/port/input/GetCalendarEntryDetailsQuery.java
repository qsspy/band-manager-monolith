package com.qsspy.calendars.query.application.entries.details.port.input;

import com.qsspy.commons.architecture.cqrs.Query;
import com.qsspy.commons.architecture.cqrs.QueryValidationException;

import java.util.UUID;

public record GetCalendarEntryDetailsQuery(
        UUID bandId,
        UUID memberId,
        UUID entryId
) implements Query {
    @Override
    public void validate() {
        if(bandId == null) {
            throw new QueryValidationException("Band Id cannot be null");
        }
        if(memberId == null) {
            throw new QueryValidationException("Member Id cannot be null");
        }
        if(entryId == null) {
            throw new QueryValidationException("Entry Id cannot be null");
        }
    }
}
