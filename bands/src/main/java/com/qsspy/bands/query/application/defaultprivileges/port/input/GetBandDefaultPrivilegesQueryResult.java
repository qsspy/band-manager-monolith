package com.qsspy.bands.query.application.defaultprivileges.port.input;

import com.qsspy.commons.architecture.cqrs.QueryResult;
import lombok.Builder;

@Builder
public record GetBandDefaultPrivilegesQueryResult(
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
) implements QueryResult { }
