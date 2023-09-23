package com.qsspy.domain.calendar;

import com.qsspy.domain.calendar.dto.CalendarEntrySpecification;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.Collections;
import java.util.UUID;


@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class CalendarEntryFactory {

    public static CalendarEntry createNewCalendarEntry(final CalendarEntrySpecification specification) {
        final var entry = CalendarEntry.builder()
                .id(new AggregateId(UUID.randomUUID()))
                .bandId(new BandId(specification.bandId()))
                .eventDate(new EventDate(specification.eventDate()))
                .eventKind(specification.eventKind())
                .amount(new Amount(specification.amount()))
                .address(specification.address() != null ? new Address(specification.address()) : null)
                .eventDuration(specification.eventDuration() != null ? new EventDuration(specification.eventDuration()) : null)
                .description(specification.description() != null ? new Description(specification.description()) : null)
                .restrictedViewerPrivileges(Collections.emptyList())
                .build();

        entry.validateCurrentState();

        return entry;
    }
}
