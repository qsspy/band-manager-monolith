package com.qsspy.authservice.infrastructure.port.repository;

import com.qsspy.authservice.application.authorizer.port.input.UserContext;

import java.util.UUID;

public record UserBandPrivilegesDto(
        boolean canAddFinanceEntries,
        boolean canAddCalendarEntries,
        boolean canEditCalendarEntries,
        boolean canDeleteCalendarEntries,
        boolean canAccessFinanceHistory
) {

    static UserBandPrivilegesDto allAllowed() {
        return new UserBandPrivilegesDto(true, true, true, true, true);
    }
}
