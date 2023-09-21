package com.qsspy.calendars.command.domain.entry;

import com.qsspy.calendars.command.domain.entry.dto.EditCalendarEntryData;
import com.qsspy.calendars.command.domain.entry.dto.RestrictedMemberPrivilegesData;
import com.qsspy.commons.architecture.ddd.DomainValidationException;
import com.qsspy.commons.architecture.ddd.AggregateRoot;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import org.springframework.lang.Nullable;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Builder(access = AccessLevel.PACKAGE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class CalendarEntry implements AggregateRoot {

    private final AggregateId id;
    private final EntryId bandId;

    private EventDate eventDate;
    private EventKind eventKind;
    private Amount amount;

    @Nullable
    private Address address;
    @Nullable
    private EventDuration eventDuration;
    @Nullable
    private Description description;

    private final List<RestrictedEntryViewerWithPrivileges> restrictedViewers;

    public CalendarEntry editEntry(final EditCalendarEntryData editData) {

        eventDate = new EventDate(editData.eventDate());
        eventKind = editData.eventKind();
        amount = new Amount(editData.amount());
        address = editData.address() != null ? new Address(editData.address()) : null;
        eventDuration = editData.eventDuration() != null ? new EventDuration(editData.eventDuration()) : null;
        description = editData.description() != null ? new Description(editData.description()) : null;

        validateCurrentState();
        return this;
    }

    public CalendarEntry editMemberEntryPrivileges(final RestrictedMemberPrivilegesData data) {

        restrictedViewers.stream()
                .filter(viewer -> viewer.getMemberId().value().equals(data.memberId()))
                .findFirst()
                .ifPresentOrElse(
                        viewer -> {
                            viewer.setCanSeeCalendarEntry(new Privilege(data.canSeeCalendarEntry()));
                            viewer.setCanSeeCalendarEntryPayment(new Privilege(data.canSeeCalendarEntryPayment()));
                            viewer.setCanSeeCalendarEntryDetails(new Privilege(data.canSeeCalendarEntryDetails()));
                        },
                        () -> {
                            restrictedViewers.add(
                                    RestrictedEntryViewerWithPrivileges.builder()
                                            .entryId(new EntryId(id.value()))
                                            .memberId(new MemberId(data.memberId()))
                                            .canSeeCalendarEntry(new Privilege(data.canSeeCalendarEntry()))
                                            .canSeeCalendarEntryPayment(new Privilege(data.canSeeCalendarEntryPayment()))
                                            .canSeeCalendarEntryDetails(new Privilege(data.canSeeCalendarEntryDetails()))
                                            .build()
                            );
                        }
                );

        validateCurrentState();
        return this;
    }

    void validateCurrentState() {
        if(id == null) {
            throw new DomainValidationException("Aggregate id cannot be null!");
        }
        if(bandId == null) {
            throw new DomainValidationException("Band id cannot be null!");
        }
        if(eventDate == null) {
            throw new DomainValidationException("Event date cannot be null!");
        }
        if(eventKind == null) {
            throw new DomainValidationException("Event kind cannot be null!");
        }
        if(amount == null) {
            throw new DomainValidationException("Amount cannot be null!");
        }
        if(restrictedViewers == null) {
            throw new DomainValidationException("Restricted viewers cannot be null!");
        }

        id.validate();
        bandId.validate();
        eventDate.validate();
        amount.validate();
        restrictedViewers.forEach(RestrictedEntryViewerWithPrivileges::validate);

        if(address != null) {
            address.validate();
        }
        if(eventDuration != null) {
            eventDuration.validate();
        }
        if(description != null) {
            description.validate();
        }
    }

    public Snapshot takeSnapshot() {
        return Snapshot.builder()
                .id(id.value())
                .bandId(bandId.value())
                .eventDate(eventDate.value())
                .eventKind(eventKind)
                .amount(amount.value())
                .address(address != null ? address.fullAddress() : null)
                .eventDuration(eventDuration != null ? eventDuration.value() : null)
                .description(description != null ? description.text() : null)
                .restrictedViewers(restrictedViewers.stream().map(RestrictedEntryViewerWithPrivileges::takeSnapshot).toList())
                .build();
    }

    @Builder
    public record Snapshot(
            UUID id,
            UUID bandId,
            LocalDateTime eventDate,
            EventKind eventKind,
            BigDecimal amount,
            @Nullable
            String address,
            @Nullable
            Duration eventDuration,
            @Nullable
            String description,
            List<RestrictedEntryViewerWithPrivileges.Snapshot> restrictedViewers
    ) { }
}
