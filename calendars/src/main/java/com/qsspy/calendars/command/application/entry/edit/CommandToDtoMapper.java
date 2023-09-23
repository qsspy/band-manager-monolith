package com.qsspy.calendars.command.application.entry.edit;

import com.qsspy.calendars.command.application.entry.edit.port.input.EditCalendarEntryCommand;
import com.qsspy.domain.calendar.dto.EditCalendarEntryData;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
final class CommandToDtoMapper {

    static EditCalendarEntryData toEditData(final EditCalendarEntryCommand command) {
        return EditCalendarEntryData.builder()
                .eventDate(command.eventDate())
                .eventKind(command.eventKind())
                .amount(command.amount())
                .address(command.address())
                .eventDuration(command.eventDuration())
                .description(command.description())
                .build();
    }
}
