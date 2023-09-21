package com.qsspy.calendars.command.application.entry.restriction.port.input;

import com.qsspy.commons.architecture.cqrs.Command;
import com.qsspy.commons.architecture.cqrs.CommandValidationException;
import lombok.Builder;

import java.util.UUID;

@Builder
public record RestrictMemberPrivilegesForEntryCommand(
        UUID initiatorsBandId,
        UUID bandId,
        UUID memberId,
        UUID entryId,
        boolean canSeeCalendarEntry,
        boolean canSeeCalendarEntryPayment,
        boolean canSeeCalendarEntryDetails
) implements Command {

    @Override
    public void validate() {
        if(initiatorsBandId == null) {
            throw new CommandValidationException("Initiator band id cannot be null!");
        }
        if(bandId == null) {
            throw new CommandValidationException("Band id cannot be null!");
        }
        if(memberId == null) {
            throw new CommandValidationException("Member id cannot be null!");
        }
        if(entryId == null) {
            throw new CommandValidationException("Entry id cannot be null!");
        }
    }
}
