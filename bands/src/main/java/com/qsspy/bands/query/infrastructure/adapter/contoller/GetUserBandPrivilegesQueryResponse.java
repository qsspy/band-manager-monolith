package com.qsspy.bands.query.infrastructure.adapter.contoller;

import lombok.Builder;

@Builder
record GetUserBandPrivilegesQueryResponse(
        boolean canAccessCalendar,
        boolean canAddCalendarEntries,
        boolean canEditCalendarEntries,
        boolean canDeleteCalendarEntries,

        boolean canAccessFinanceHistory,
        boolean canAddFinanceEntries,

        boolean canSeeFinanceIncomeEntries,
        boolean canSeeFinanceOutcomeEntries
) { }
