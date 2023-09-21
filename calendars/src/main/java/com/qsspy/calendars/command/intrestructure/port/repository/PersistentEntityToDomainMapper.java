package com.qsspy.calendars.command.intrestructure.port.repository;

import com.qsspy.calendars.command.domain.entry.CalendarEntry;
import com.qsspy.calendars.command.domain.entry.CalendarEntryFactory;
import com.qsspy.calendars.command.domain.entry.EventKind;
import com.qsspy.calendars.command.domain.entry.RestrictedEntryViewerWithPrivileges;
import com.qsspy.jpadatamodel.entity.CalendarEntryEntity;
import com.qsspy.jpadatamodel.entity.RestrictedCalendarViewerPrivilegesEntity;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.List;

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
                        .restrictedViewers(mapRestrictedViewers(entity.getRestrictedViewerPrivileges()))
                        .build()
        );
    }

    private static List<RestrictedEntryViewerWithPrivileges.Snapshot> mapRestrictedViewers(final List<RestrictedCalendarViewerPrivilegesEntity> restrictedViewerPrivileges) {
        return restrictedViewerPrivileges.stream()
                .map(privilege ->
                        RestrictedEntryViewerWithPrivileges.Snapshot.builder()
                                .memberId(privilege.getMemberId())
                                .entryId(privilege.getEntryId())
                                .canSeeCalendarEntry(privilege.isCanSeeCalendarEntry())
                                .canSeeCalendarEntryPayment(privilege.isCanSeeCalendarEntryPayment())
                                .canSeeCalendarEntryDetails(privilege.isCanSeeCalendarEntryDetails())
                                .build())
                .toList();
    }
}
