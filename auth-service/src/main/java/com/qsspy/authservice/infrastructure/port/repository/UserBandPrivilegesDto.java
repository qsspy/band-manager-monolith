package com.qsspy.authservice.infrastructure.port.repository;

import com.qsspy.authservice.application.authorizer.port.input.UserContext;

import java.util.UUID;

public record UserBandPrivilegesDto(
        boolean canAddFinanceEntries
) {

    static UserBandPrivilegesDto allAllowed() {
        return new UserBandPrivilegesDto(true);
    }
}
