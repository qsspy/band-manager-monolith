package com.qsspy.authservice.application.login.port.output;

import org.springframework.lang.Nullable;

import java.util.UUID;

public record UserLoginDTO(
        UUID userId,
        @Nullable
        UUID ownBandId,
        @Nullable
        UUID memberBandId
) {

    public UUID getBandMembershipUuid() {
        return ownBandId != null ? ownBandId : memberBandId;
    }
}
