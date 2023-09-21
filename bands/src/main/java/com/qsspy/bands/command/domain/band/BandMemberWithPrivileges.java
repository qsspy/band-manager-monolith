package com.qsspy.bands.command.domain.band;

import com.qsspy.commons.architecture.ddd.DomainValidationException;
import com.qsspy.commons.architecture.ddd.Entity;
import lombok.*;

import java.util.UUID;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder(access = AccessLevel.PACKAGE)
@Getter(AccessLevel.PACKAGE)
@Setter(AccessLevel.PACKAGE)
public class BandMemberWithPrivileges implements Entity {

    private final BandId bandId;
    private final MemberId memberId;

    private Privilege canAccessCalendar;
    private Privilege canAddCalendarEntries;
    private Privilege canEditCalendarEntries;
    private Privilege canDeleteCalendarEntries;

    private Privilege canAccessFinanceHistory;
    private Privilege canAddFinanceEntries;

    private Privilege canSeeFinanceIncomeEntries;
    private Privilege canSeeFinanceOutcomeEntries;

    @Override
    public void validate() {
        if(bandId == null) {
            throw new DomainValidationException("Band Id cannot be null!");
        }
        if(memberId == null) {
            throw new DomainValidationException("Member Id cannot be null!");
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

        bandId.validate();
        memberId.validate();
        canAccessCalendar.validate();
        canAddCalendarEntries.validate();
        canEditCalendarEntries.validate();
        canDeleteCalendarEntries.validate();

        canAccessFinanceHistory.validate();
        canAddFinanceEntries.validate();

        canSeeFinanceIncomeEntries.validate();
        canSeeFinanceOutcomeEntries.validate();
    }

    Snapshot takeSnapshot() {
        return Snapshot.builder()
                .bandId(bandId.value())
                .memberId(memberId.value())
                .canAccessCalendar(canAccessCalendar.isAllowed())
                .canAddCalendarEntries(canAddCalendarEntries.isAllowed())
                .canEditCalendarEntries(canEditCalendarEntries.isAllowed())
                .canDeleteCalendarEntries(canDeleteCalendarEntries.isAllowed())
                .canAccessFinanceHistory(canAccessFinanceHistory.isAllowed())
                .canAddFinanceEntries(canAddFinanceEntries.isAllowed())
                .canSeeFinanceIncomeEntries(canSeeFinanceIncomeEntries.isAllowed())
                .canSeeFinanceOutcomeEntries(canSeeFinanceOutcomeEntries.isAllowed())
                .build();
    }

    @Builder
    public record Snapshot(
            UUID bandId,
            UUID memberId,
            boolean canAccessCalendar,
            boolean canAddCalendarEntries,
            boolean canEditCalendarEntries,
            boolean canDeleteCalendarEntries,
            boolean canAccessFinanceHistory,
            boolean canAddFinanceEntries,
            boolean canSeeFinanceIncomeEntries,
            boolean canSeeFinanceOutcomeEntries
    ) {}
}
