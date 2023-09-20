package com.qsspy.authservice.infrastructure.port.repository;

import java.util.UUID;

public record UserDataDto(
        UUID userId,
        String email,
        String firstName,
        String lastName,
        UUID userMemberBandId,
        UUID userOwnBandId
) {
}
