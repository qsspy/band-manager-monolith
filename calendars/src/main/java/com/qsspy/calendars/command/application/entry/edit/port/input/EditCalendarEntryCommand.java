package com.qsspy.calendars.command.application.entry.edit.port.input;

import com.qsspy.commons.architecture.cqrs.Command;
import com.qsspy.commons.architecture.cqrs.CommandValidationException;
import com.qsspy.domain.calendar.EventKind;
import lombok.Builder;
import org.springframework.lang.Nullable;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.UUID;

@Builder
public record EditCalendarEntryCommand(
        UUID initiatorsBandId,
        UUID bandId,
        UUID entryId,
        LocalDateTime eventDate,
        EventKind eventKind,
        BigDecimal amount,
        @Nullable
        String address,
        @Nullable
        Duration eventDuration,
        @Nullable
        String description
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
        if(eventDate == null) {
            throw new CommandValidationException("Event date id cannot be null");
        }
        if(eventKind == null) {
            throw new CommandValidationException("Event kind id cannot be null");
        }
        if(amount == null || amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new CommandValidationException("Amount cannot be negative or null");
        }
    }
}
