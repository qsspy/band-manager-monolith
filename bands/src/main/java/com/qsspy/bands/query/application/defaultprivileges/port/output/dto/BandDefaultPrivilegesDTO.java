package com.qsspy.bands.query.application.defaultprivileges.port.output.dto;

public record BandDefaultPrivilegesDTO(
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
) {
}
