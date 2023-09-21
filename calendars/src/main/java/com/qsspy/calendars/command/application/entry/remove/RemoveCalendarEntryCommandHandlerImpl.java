package com.qsspy.calendars.command.application.entry.remove;

import com.qsspy.calendars.command.application.entry.remove.port.input.RemoveCalendarEntryCommand;
import com.qsspy.calendars.command.application.entry.remove.port.input.RemoveCalendarEntryCommandHandler;
import com.qsspy.calendars.command.application.entry.remove.port.output.CalendarEntryDeleteRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
class RemoveCalendarEntryCommandHandlerImpl implements RemoveCalendarEntryCommandHandler {

    private final CalendarEntryDeleteRepository deleteRepository;

    @Override
    public void handle(final RemoveCalendarEntryCommand command) {
        command.validate();

        if(!command.initiatorsBandId().equals(command.bandId())) {
            throw new IllegalStateException("Member can only delete entries from its own band!");
        }

        deleteRepository.remove(command.bandId(), command.entryId());
    }
}
