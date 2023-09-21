package com.qsspy.bands.query.infrastructure.adapter.contoller;

import com.qsspy.commons.architecture.cqrs.QueryResult;
import lombok.Builder;

@Builder
record GetBandDefaultPrivilegesQueryResponse(
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
