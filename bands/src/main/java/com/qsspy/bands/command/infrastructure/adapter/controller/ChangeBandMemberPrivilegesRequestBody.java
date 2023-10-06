package com.qsspy.bands.command.infrastructure.adapter.controller;

import org.springframework.lang.Nullable;

record ChangeBandMemberPrivilegesRequestBody(
        @Nullable
        Boolean canAccessCalendar,
        @Nullable
        Boolean canAddCalendarEntries,
        @Nullable
        Boolean canEditCalendarEntries,
        @Nullable
        Boolean canDeleteCalendarEntries,
        @Nullable
        Boolean canAccessFinanceHistory,
        @Nullable
        Boolean canAddFinanceEntries,
        @Nullable
        Boolean canSeeFinanceIncomeEntries,
        @Nullable
        Boolean canSeeFinanceOutcomeEntries
) {
}
