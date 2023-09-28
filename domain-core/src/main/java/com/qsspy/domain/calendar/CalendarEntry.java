package com.qsspy.domain.calendar;

import com.qsspy.commons.architecture.ddd.AggregateRoot;
import com.qsspy.commons.architecture.ddd.DomainValidationException;
import com.qsspy.domain.calendar.dto.EditCalendarEntryData;
import com.qsspy.domain.calendar.dto.RestrictedMemberPrivilegesData;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;

import java.util.List;

@Builder(access = AccessLevel.PACKAGE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Entity(name = "CALENDAR_ENTRIES")
public class CalendarEntry implements AggregateRoot {
    @EmbeddedId
    private AggregateId id;

    @Embedded
    private BandId bandId;

    @Embedded
    private EventDate eventDate;

    @Column(name = "EVENT_KIND")
    @Enumerated(value = EnumType.STRING)
    private EventKind eventKind;

    @Embedded
    private Amount amount;

    @Nullable
    @Embedded
    private Address address;

    @Nullable
    @Embedded
    private EventDuration eventDuration;

    @Nullable
    @Embedded
    private Description description;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "ENTRY_ID", referencedColumnName = "ID")
    private List<RestrictedCalendarViewerPrivileges> restrictedViewerPrivileges;

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

        restrictedViewerPrivileges.stream()
                .filter(viewer -> viewer.getMemberId().equals(data.memberId()))
                .findFirst()
                .ifPresentOrElse(
                        viewer -> {
                            viewer.changeCanSeeCalendarEntryPrivilege(data.canSeeCalendarEntry());
                            viewer.changeCanSeeCalendarEntryPaymentPrivilege(data.canSeeCalendarEntryPayment());
                            viewer.changeCanSeeCalendarEntryDetailsPrivilege(data.canSeeCalendarEntryDetails());
                        },
                        () -> {
                            restrictedViewerPrivileges.add(
                                    RestrictedCalendarViewerPrivileges.builder()
                                            .id(new RestrictionId(id.getValue(), data.memberId()))
                                            .canSeeCalendarEntry(CanSeeCalendarEntryPrivilege.from(data.canSeeCalendarEntry()))
                                            .canSeeCalendarEntryPayment(CanSeeCalendarEntryPaymentPrivilege.from(data.canSeeCalendarEntryPayment()))
                                            .canSeeCalendarEntryDetails(CanSeeCalendarEntryDetailsPrivilege.from(data.canSeeCalendarEntryDetails()))
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
        if(restrictedViewerPrivileges == null) {
            throw new DomainValidationException("Restricted viewers cannot be null!");
        }

        id.validate();
        bandId.validate();
        eventDate.validate();
        amount.validate();
        restrictedViewerPrivileges.forEach(RestrictedCalendarViewerPrivileges::validate);

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
}
