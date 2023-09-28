package com.qsspy.calendars.command.application.entry.restriction;

import com.qsspy.calendars.command.application.entry.common.port.output.CalendarEntryGetRepository;
import com.qsspy.calendars.command.application.entry.common.port.output.CalendarEntrySaveRepository;
import com.qsspy.calendars.command.application.entry.restriction.port.input.RestrictMemberPrivilegesForEntryCommand;
import com.qsspy.calendars.command.application.entry.restriction.port.input.RestrictMemberPrivilegesForEntryCommandHandler;
import com.qsspy.domain.calendar.CalendarEntry;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
class RestrictMemberPrivilegesForEntryCommandHandlerImpl implements RestrictMemberPrivilegesForEntryCommandHandler {

    private final CalendarEntryGetRepository getRepository;
    private final CalendarEntrySaveRepository saveRepository;

    @Override
    public void handle(final RestrictMemberPrivilegesForEntryCommand command) {
        command.validate();

        if(!command.initiatorsBandId().equals(command.bandId())) {
            throw new IllegalStateException("Admin cannot edit his own privileges!");
        }

        getRepository
                .findByBandIdAndId(command.bandId(), command.entryId())
                .ifPresentOrElse(
                        entry -> editMemberPrivilegesAndSave(entry, command),
                        () -> { throw new IllegalStateException("Could not find band for editing the member entry privileges"); }
                );
    }

    private void editMemberPrivilegesAndSave(final CalendarEntry entry, final RestrictMemberPrivilegesForEntryCommand command) {
        final var editData = CommandToDtoMapper.toDomainData(command);
        entry.editMemberEntryPrivileges(editData);
        saveRepository.save(entry);
    }
}
