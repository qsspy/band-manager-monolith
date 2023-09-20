package com.qsspy.authservice.application.authorizer.port.input;

import lombok.Builder;
import org.springframework.lang.Nullable;

import java.util.UUID;

@Builder
public record UserContext(
        UUID userId,
        String email,
        String firstName,
        String lastName,
        @Nullable
        UUID userMemberBandId,
        @Nullable
        UUID userOwnBandId,
        @Nullable
        Privileges bandPrivileges
) {

    public UUID userMembershipBandId() {
        return userMemberBandId != null ? userMemberBandId : userOwnBandId;
    }

    @Builder
    public record Privileges(
            boolean canAddFinanceEntries
    ) { }
}
