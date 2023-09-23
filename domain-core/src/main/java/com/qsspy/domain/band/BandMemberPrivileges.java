package com.qsspy.domain.band;

import com.qsspy.commons.architecture.ddd.DomainEntity;
import com.qsspy.commons.architecture.ddd.DomainValidationException;
import jakarta.persistence.*;
import lombok.*;

@Builder(access = AccessLevel.PACKAGE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Entity(name = "BAND_MEMBER_PRIVILEGES")
@Getter(AccessLevel.PACKAGE)
@Setter(AccessLevel.PACKAGE)
public class BandMemberPrivileges implements DomainEntity {

    @EmbeddedId
    private BandMemberPrivilegesId id;

    @Embedded
    private CanAccessCalendarPrivilege canAccessCalendar;

    @Embedded
    private CanAddCalendarEntriesPrivilege canAddCalendarEntries;

    @Embedded
    private CanEditCalendarEntriesPrivilege canEditCalendarEntries;

    @Embedded
    private CanDeleteCalendarEntriesPrivilege canDeleteCalendarEntries;

    @Embedded
    private CanAccessFinanceHistoryPrivilege canAccessFinanceHistory;

    @Embedded
    private CanAddFinanceEntriesPrivilege canAddFinanceEntries;

    @Embedded
    private CanSeeFinanceIncomeEntriesPrivilege canSeeFinanceIncomeEntries;

    @Embedded
    private CanSeeFinanceOutcomeEntriesPrivilege canSeeFinanceOutcomeEntries;

    @Override
    public void validate() {
        if(id == null) {
            throw new DomainValidationException("Id cannot be null!");
        }
        if(canAccessCalendar == null) {
            throw new DomainValidationException("'canAccessCalendar' privilege cannot be null!");
        }
        if(canAddCalendarEntries == null) {
            throw new DomainValidationException("'canAddCalendarEntries' privilege cannot be null!");
        }
        if(canEditCalendarEntries == null) {
            throw new DomainValidationException("'canEditCalendarEntries' privilege cannot be null!");
        }
        if(canDeleteCalendarEntries == null) {
            throw new DomainValidationException("'canDeleteCalendarEntries' privilege cannot be null!");
        }
        if(canAccessFinanceHistory == null) {
            throw new DomainValidationException("'canAccessFinanceHistory' privilege cannot be null!");
        }
        if(canAddFinanceEntries == null) {
            throw new DomainValidationException("'canAddFinanceEntries' privilege cannot be null!");
        }
        if(canSeeFinanceIncomeEntries == null) {
            throw new DomainValidationException("'canSeeFinanceIncomeEntries' privilege cannot be null!");
        }
        if(canSeeFinanceOutcomeEntries == null) {
            throw new DomainValidationException("'canSeeFinanceOutcomeEntries' privilege cannot be null!");
        }

        id.validate();
        canAccessCalendar.validate();
        canAddCalendarEntries.validate();
        canEditCalendarEntries.validate();
        canDeleteCalendarEntries.validate();

        canAccessFinanceHistory.validate();
        canAddFinanceEntries.validate();

        canSeeFinanceIncomeEntries.validate();
        canSeeFinanceOutcomeEntries.validate();
    }
}
