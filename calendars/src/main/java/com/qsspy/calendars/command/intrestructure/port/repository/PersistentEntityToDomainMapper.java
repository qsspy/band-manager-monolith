package com.qsspy.calendars.command.intrestructure.port.repository;

import com.qsspy.calendars.command.domain.entry.CalendarEntry;
import com.qsspy.calendars.command.domain.entry.CalendarEntryFactory;
import com.qsspy.calendars.command.domain.entry.EventKind;
import com.qsspy.jpadatamodel.entity.CalendarEntryEntity;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
final class PersistentEntityToDomainMapper {

    static CalendarEntry toEntity(final CalendarEntryEntity entity) {

        return CalendarEntryFactory.instantiateFromSnapshot(
                CalendarEntry.Snapshot.builder()
                        .id(entity.getId())
                        .bandId(entity.getBandId())
                        .eventDate(entity.getEventDate())
                        .eventKind(EventKind.valueOf(entity.getEventKind()))
                        .amount(entity.getAmount())
                        .address(entity.getAddress())
                        .eventDuration(entity.getEventDuration())
                        .description(entity.getDescription())
                        .build()
        );
    }
}
