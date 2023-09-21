package com.qsspy.calendars.command.intrestructure.port.repository;

import com.qsspy.calendars.command.domain.entry.CalendarEntry;
import com.qsspy.calendars.command.domain.entry.RestrictedEntryViewerWithPrivileges;
import com.qsspy.jpadatamodel.entity.CalendarEntryEntity;
import com.qsspy.jpadatamodel.entity.RestrictedCalendarViewerPrivilegesEntity;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.List;

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
                .restrictedViewerPrivileges(mapRestrictedViewers(snapshot.restrictedViewers()))
                .build();
    }

    private static List<RestrictedCalendarViewerPrivilegesEntity> mapRestrictedViewers(final List<RestrictedEntryViewerWithPrivileges.Snapshot> restrictedViewerPrivileges) {
        return restrictedViewerPrivileges.stream()
                .map(privilege ->
                        RestrictedCalendarViewerPrivilegesEntity.builder()
                                .memberId(privilege.memberId())
                                .entryId(privilege.entryId())
                                .canSeeCalendarEntry(privilege.canSeeCalendarEntry())
                                .canSeeCalendarEntryPayment(privilege.canSeeCalendarEntryPayment())
                                .canSeeCalendarEntryDetails(privilege.canSeeCalendarEntryDetails())
                                .build())
                .toList();
    }
}
