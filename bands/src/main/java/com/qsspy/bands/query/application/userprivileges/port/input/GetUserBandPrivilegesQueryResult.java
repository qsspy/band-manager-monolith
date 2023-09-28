package com.qsspy.bands.query.application.userprivileges.port.input;

import com.qsspy.commons.architecture.cqrs.QueryResult;
import lombok.Builder;

@Builder
public record GetUserBandPrivilegesQueryResult(
        boolean canAccessCalendar,
        boolean canAddCalendarEntries,
        boolean canEditCalendarEntries,
        boolean canDeleteCalendarEntries,

        boolean canAccessFinanceHistory,
        boolean canAddFinanceEntries,

        boolean canSeeFinanceIncomeEntries,
        boolean canSeeFinanceOutcomeEntries
) implements QueryResult { }
