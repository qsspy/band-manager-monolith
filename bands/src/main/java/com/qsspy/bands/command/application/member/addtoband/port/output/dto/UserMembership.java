package com.qsspy.bands.command.application.member.addtoband.port.output.dto;

import org.springframework.lang.Nullable;

import java.util.UUID;

public record UserMembership(
        UUID userId,
        @Nullable
        UUID ownedBandId,
        @Nullable
        UUID memberBandId
        ) { }
