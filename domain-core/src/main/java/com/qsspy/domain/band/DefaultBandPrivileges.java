package com.qsspy.domain.band;

import com.qsspy.commons.architecture.ddd.DomainEntity;
import com.qsspy.commons.architecture.ddd.DomainValidationException;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Builder(access = AccessLevel.PACKAGE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Entity(name = "DEFAULT_BAND_PRIVILEGES")
@Getter(AccessLevel.PACKAGE)
@Setter(AccessLevel.PACKAGE)
class DefaultBandPrivileges implements DomainEntity {
    @EmbeddedId
    private EntityId id;

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

    @Embedded
    private CanSeeCalendarEntryByDefaultPrivilege canSeeCalendarEntryByDefault;

    @Embedded
    private CanSeeCalendarEntryPaymentByDefaultPrivilege canSeeCalendarEntryPaymentByDefault;

    @Embedded
    private CanSeeCalendarEntryDetailsByDefaultPrivilege canSeeCalendarEntryDetailsByDefault;

    static DefaultBandPrivileges newBaseDefaultPrivileges(final EntityId entityId) {
        return DefaultBandPrivileges.builder()
                .id(entityId)
                .canAccessCalendar(CanAccessCalendarPrivilege.from(true))
                .canAddCalendarEntries(CanAddCalendarEntriesPrivilege.from(false))
                .canEditCalendarEntries(CanEditCalendarEntriesPrivilege.from(false))
                .canDeleteCalendarEntries(CanDeleteCalendarEntriesPrivilege.from(false))
                .canAccessFinanceHistory(CanAccessFinanceHistoryPrivilege.from(true))
                .canAddFinanceEntries(CanAddFinanceEntriesPrivilege.from(false))
                .canSeeFinanceIncomeEntries(CanSeeFinanceIncomeEntriesPrivilege.from(true))
                .canSeeFinanceOutcomeEntries(CanSeeFinanceOutcomeEntriesPrivilege.from(true))
                .canSeeCalendarEntryByDefault(CanSeeCalendarEntryByDefaultPrivilege.from(true))
                .canSeeCalendarEntryPaymentByDefault(CanSeeCalendarEntryPaymentByDefaultPrivilege.from(true))
                .canSeeCalendarEntryDetailsByDefault(CanSeeCalendarEntryDetailsByDefaultPrivilege.from(true))
                .build();
    }

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
        if(canSeeCalendarEntryByDefault == null) {
            throw new DomainValidationException("'canSeeCalendarEntryByDefault' privilege cannot be null!");
        }
        if(canSeeCalendarEntryPaymentByDefault == null) {
            throw new DomainValidationException("'canSeeCalendarEntryPaymentByDefault' privilege cannot be null!");
        }
        if(canSeeCalendarEntryDetailsByDefault == null) {
            throw new DomainValidationException("'canSeeCalendarEntryDetailsByDefault' privilege cannot be null!");
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

        canSeeCalendarEntryByDefault.validate();
        canSeeCalendarEntryPaymentByDefault.validate();
        canSeeCalendarEntryDetailsByDefault.validate();
    }
}
