package com.qsspy.authservice.infrastructure.port.repository;

public record UserBandPrivilegesDto(
        boolean canAddFinanceEntries,
        boolean canAddCalendarEntries,
        boolean canEditCalendarEntries,
        boolean canDeleteCalendarEntries,
        boolean canAccessFinanceHistory,
        boolean canAccessCalendar
) {

    static UserBandPrivilegesDto allAllowed() {
        return new UserBandPrivilegesDto(true, true, true, true, true,true);
    }
}
