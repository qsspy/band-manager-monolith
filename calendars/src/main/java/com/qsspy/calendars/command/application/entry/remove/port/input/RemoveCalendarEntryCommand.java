package com.qsspy.calendars.command.application.entry.remove.port.input;

import com.qsspy.commons.architecture.cqrs.Command;
import com.qsspy.commons.architecture.cqrs.CommandValidationException;
import lombok.Builder;

import java.util.UUID;

@Builder
public record RemoveCalendarEntryCommand(
        UUID initiatorsBandId,
        UUID bandId,
        UUID entryId
) implements Command {
    @Override
    public void validate() {
        if(initiatorsBandId == null) {
            throw new CommandValidationException("Initiator band Id cannot be null");
        }
        if(bandId == null) {
            throw new CommandValidationException("Band id cannot be null");
        }
        if(entryId == null) {
            throw new CommandValidationException("Entry id cannot be null");
        }
    }
}
