package com.qsspy.calendars.command.application.entry.create;

import com.qsspy.calendars.command.application.entry.create.port.input.CreateCalendarEntryCommand;
import com.qsspy.domain.calendar.dto.CalendarEntrySpecification;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
final class CommandToDtoMapper {

    static CalendarEntrySpecification toSpecification(final CreateCalendarEntryCommand command) {
        return CalendarEntrySpecification.builder()
                .bandId(command.bandId())
                .eventDate(command.eventDate())
                .eventKind(command.eventKind())
                .amount(command.amount())
                .address(command.address())
                .eventDuration(command.eventDuration())
                .description(command.description())
                .build();
    }
}
