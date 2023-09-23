package com.qsspy.domain.calendar;

import com.qsspy.commons.architecture.ddd.DomainEntity;
import com.qsspy.commons.architecture.ddd.DomainValidationException;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Builder(access = AccessLevel.PACKAGE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity(name = "RESTRICTED_CALENDAR_ENTRY_VIEWER_PRIVILEGES")
class RestrictedCalendarViewerPrivileges implements DomainEntity {

    @EmbeddedId
    private RestrictionId id;

    @Embedded
    private CanSeeCalendarEntryPrivilege canSeeCalendarEntry;

    @Embedded
    private CanSeeCalendarEntryPaymentPrivilege canSeeCalendarEntryPayment;

    @Embedded
    private CanSeeCalendarEntryDetailsPrivilege canSeeCalendarEntryDetails;

    @Override
    public void validate() {
        if(id == null) {
            throw new DomainValidationException("Id cannot be null!");
        }
        if(canSeeCalendarEntry == null) {
            throw new DomainValidationException("'canSeeCalendarEntry' privilege cannot be null!");
        }
        if(canSeeCalendarEntryPayment == null) {
            throw new DomainValidationException("'canSeeCalendarEntryPayment' privilege cannot be null!");
        }
        if(canSeeCalendarEntryDetails == null) {
            throw new DomainValidationException("'canSeeCalendarEntryDetails' privilege cannot be null!");
        }

        id.validate();
        canSeeCalendarEntry.validate();
        canSeeCalendarEntryPayment.validate();
        canSeeCalendarEntryDetails.validate();
    }

    void changeCanSeeCalendarEntryPrivilege(final boolean isAllowed) {
        canSeeCalendarEntry = CanSeeCalendarEntryPrivilege.from(isAllowed);
    }

    void changeCanSeeCalendarEntryPaymentPrivilege(final boolean isAllowed) {
        canSeeCalendarEntryPayment = CanSeeCalendarEntryPaymentPrivilege.from(isAllowed);
    }

    void changeCanSeeCalendarEntryDetailsPrivilege(final boolean isAllowed) {
        canSeeCalendarEntryDetails = CanSeeCalendarEntryDetailsPrivilege.from(isAllowed);
    }

    UUID getMemberId() {
        return id.getMemberId();
    }
}
