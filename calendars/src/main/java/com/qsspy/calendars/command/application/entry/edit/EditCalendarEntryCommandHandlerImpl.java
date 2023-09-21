package com.qsspy.calendars.command.application.entry.edit;

import com.qsspy.calendars.command.application.entry.common.port.output.CalendarEntrySaveRepository;
import com.qsspy.calendars.command.application.entry.edit.port.input.EditCalendarEntryCommand;
import com.qsspy.calendars.command.application.entry.edit.port.input.EditCalendarEntryCommandHandler;
import com.qsspy.calendars.command.application.entry.common.port.output.CalendarEntryGetRepository;
import com.qsspy.calendars.command.domain.entry.CalendarEntry;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Transactional
@Service
@RequiredArgsConstructor
class EditCalendarEntryCommandHandlerImpl implements EditCalendarEntryCommandHandler {

    private final CalendarEntryGetRepository getRepository;
    private final CalendarEntrySaveRepository saveRepository;

    @Override
    public void handle(final EditCalendarEntryCommand command) {
        command.validate();

        if(!command.initiatorsBandId().equals(command.bandId())) {
            throw new IllegalStateException("Member can only edit entries from its own band!");
        }

        getRepository
                .findByBandIdAndId(command.bandId(), command.entryId())
                .ifPresentOrElse(
                        entry -> editEntryAndSave(entry, command),
                        () -> { throw new IllegalStateException("Could not find band for editing the entry"); }
                );
    }

    private void editEntryAndSave(final CalendarEntry entry, final EditCalendarEntryCommand command) {
        final var editData = CommandToDtoMapper.toEditData(command);
        entry.editEntry(editData);
        saveRepository.save(entry);
    }
}
