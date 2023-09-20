package com.qsspy.bands.command.domain.band.dto;

import lombok.Builder;
import org.springframework.lang.Nullable;

@Builder
public record DefaultPrivilegesChangeSpecification(
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
        Boolean canSeeFinanceOutcomeEntries,
        @Nullable
        Boolean canSeeCalendarEntryByDefault,
        @Nullable
        Boolean canSeeCalendarEntryPaymentByDefault,
        @Nullable
        Boolean canSeeCalendarEntryDetailsByDefault
) {
}
