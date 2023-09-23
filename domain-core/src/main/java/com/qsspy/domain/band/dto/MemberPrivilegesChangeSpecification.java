package com.qsspy.domain.band.dto;

import lombok.Builder;
import org.springframework.lang.Nullable;

@Builder
public record MemberPrivilegesChangeSpecification(
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
