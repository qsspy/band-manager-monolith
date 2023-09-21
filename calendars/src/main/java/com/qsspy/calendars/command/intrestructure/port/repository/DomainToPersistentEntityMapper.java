package com.qsspy.calendars.command.intrestructure.port.repository;

import com.qsspy.calendars.command.domain.entry.CalendarEntry;
import com.qsspy.jpadatamodel.entity.CalendarEntryEntity;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
final class DomainToPersistentEntityMapper {

    static CalendarEntryEntity toEntity(final CalendarEntry calendarEntry) {

        final var snapshot = calendarEntry.takeSnapshot();

        return CalendarEntryEntity.builder()
                .id(snapshot.id())
                .bandId(snapshot.bandId())
                .eventDate(snapshot.eventDate())
                .eventKind(snapshot.eventKind().toString())
                .amount(snapshot.amount())
                .address(snapshot.address())
                .eventDuration(snapshot.eventDuration())
                .description(snapshot.description())
                .build();
    }
}
