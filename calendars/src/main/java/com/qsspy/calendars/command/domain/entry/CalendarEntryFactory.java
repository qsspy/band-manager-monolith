package com.qsspy.calendars.command.domain.entry;

import com.qsspy.calendars.command.domain.entry.dto.CalendarEntrySpecification;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;


@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class CalendarEntryFactory {

    public static CalendarEntry createNewCalendarEntry(final CalendarEntrySpecification specification) {
        final var entry = CalendarEntry.builder()
                .id(new AggregateId(UUID.randomUUID()))
                .bandId(new EntryId(specification.bandId()))
                .eventDate(new EventDate(specification.eventDate()))
                .eventKind(specification.eventKind())
                .amount(new Amount(specification.amount()))
                .address(specification.address() != null ? new Address(specification.address()) : null)
                .eventDuration(specification.eventDuration() != null ? new EventDuration(specification.eventDuration()) : null)
                .description(specification.description() != null ? new Description(specification.description()) : null)
                .restrictedViewers(Collections.emptyList())
                .build();

        entry.validateCurrentState();

        return entry;
    }

    public static CalendarEntry instantiateFromSnapshot(final CalendarEntry.Snapshot snapshot) {
        final var entry = CalendarEntry.builder()
                .id(new AggregateId(snapshot.id()))
                .bandId(new EntryId(snapshot.bandId()))
                .eventDate(new EventDate(snapshot.eventDate()))
                .eventKind(snapshot.eventKind())
                .amount(new Amount(snapshot.amount()))
                .address(snapshot.address() != null ? new Address(snapshot.address()) : null)
                .eventDuration(snapshot.eventDuration() != null ? new EventDuration(snapshot.eventDuration()) : null)
                .description(snapshot.description() != null ? new Description(snapshot.description()) : null)
                .restrictedViewers(mapRestrictedViewers(snapshot.restrictedViewers()))
                .build();

        entry.validateCurrentState();

        return entry;
    }

    private static List<RestrictedEntryViewerWithPrivileges> mapRestrictedViewers(final List<RestrictedEntryViewerWithPrivileges.Snapshot> snapshots) {
        return snapshots.stream()
                .map(snapshot ->
                        RestrictedEntryViewerWithPrivileges.builder()
                                .entryId(new EntryId(snapshot.entryId()))
                                .memberId(new MemberId(snapshot.memberId()))
                                .canSeeCalendarEntry(new Privilege(snapshot.canSeeCalendarEntry()))
                                .canSeeCalendarEntryPayment(new Privilege(snapshot.canSeeCalendarEntryPayment()))
                                .canSeeCalendarEntryDetails(new Privilege(snapshot.canSeeCalendarEntryDetails()))
                                .build()
                )
                .collect(Collectors.toList());
    }
}
