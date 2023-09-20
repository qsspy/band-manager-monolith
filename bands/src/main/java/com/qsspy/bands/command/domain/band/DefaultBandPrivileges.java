package com.qsspy.bands.command.domain.band;

import com.qsspy.commons.architecture.cqrs.DomainValidationException;
import com.qsspy.commons.architecture.ddd.Entity;
import lombok.*;

import java.util.UUID;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder(access = AccessLevel.PACKAGE)
@Getter(AccessLevel.PACKAGE)
@Setter(AccessLevel.PACKAGE)
public class DefaultBandPrivileges implements Entity {

    private final EntityId id;

    private Privilege canAccessCalendar;
    private Privilege canAddCalendarEntries;
    private Privilege canEditCalendarEntries;
    private Privilege canDeleteCalendarEntries;

    private Privilege canAccessFinanceHistory;
    private Privilege canAddFinanceEntries;

    private Privilege canSeeFinanceIncomeEntries;
    private Privilege canSeeFinanceOutcomeEntries;

    private Privilege canSeeCalendarEntryByDefault;
    private Privilege canSeeCalendarEntryPaymentByDefault;
    private Privilege canSeeCalendarEntryDetailsByDefault;

    static DefaultBandPrivileges newBaseDefaultPrivileges(final EntityId entityId) {
        return DefaultBandPrivileges.builder()
                .id(entityId)
                .canAccessCalendar(Privilege.allowed())
                .canAddCalendarEntries(Privilege.forbidden())
                .canEditCalendarEntries(Privilege.forbidden())
                .canDeleteCalendarEntries(Privilege.forbidden())
                .canAccessFinanceHistory(Privilege.allowed())
                .canAddFinanceEntries(Privilege.forbidden())
                .canSeeFinanceIncomeEntries(Privilege.allowed())
                .canSeeFinanceOutcomeEntries(Privilege.allowed())
                .canSeeCalendarEntryByDefault(Privilege.allowed())
                .canSeeCalendarEntryPaymentByDefault(Privilege.allowed())
                .canSeeCalendarEntryDetailsByDefault(Privilege.allowed())
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

    Snapshot takeSnapshot() {
        return Snapshot.builder()
                .id(id.value())
                .canAccessCalendar(canAccessCalendar.isAllowed())
                .canAddCalendarEntries(canAddCalendarEntries.isAllowed())
                .canEditCalendarEntries(canEditCalendarEntries.isAllowed())
                .canDeleteCalendarEntries(canDeleteCalendarEntries.isAllowed())
                .canAccessFinanceHistory(canAccessFinanceHistory.isAllowed())
                .canAddFinanceEntries(canAddFinanceEntries.isAllowed())
                .canSeeFinanceIncomeEntries(canSeeFinanceIncomeEntries.isAllowed())
                .canSeeFinanceOutcomeEntries(canSeeFinanceOutcomeEntries.isAllowed())
                .canSeeCalendarEntryByDefault(canSeeCalendarEntryByDefault.isAllowed())
                .canSeeCalendarEntryPaymentByDefault(canSeeCalendarEntryPaymentByDefault.isAllowed())
                .canSeeCalendarEntryDetailsByDefault(canSeeCalendarEntryDetailsByDefault.isAllowed())
                .build();
    }

    @Builder
    public record Snapshot(
            UUID id,
            boolean canAccessCalendar,
            boolean canAddCalendarEntries,
            boolean canEditCalendarEntries,
            boolean canDeleteCalendarEntries,
            boolean canAccessFinanceHistory,
            boolean canAddFinanceEntries,
            boolean canSeeFinanceIncomeEntries,
            boolean canSeeFinanceOutcomeEntries,
            boolean canSeeCalendarEntryByDefault,
            boolean canSeeCalendarEntryPaymentByDefault,
            boolean canSeeCalendarEntryDetailsByDefault
    ) {}
}
