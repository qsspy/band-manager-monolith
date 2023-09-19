package com.qsspy.authservice.application.authorizer.port.input;

import java.util.UUID;

public record UserContext(
        UUID userId,
        String email,
        String firstName,
        String lastName,
        UUID userMemberBandId,
        UUID userOwnBandId
) { }
