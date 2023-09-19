package com.qsspy.bands.command.domain.band.dto;

import org.springframework.lang.Nullable;

import java.util.UUID;

public record BandCreationData(
        UUID creatorId,
        String bandName
) { }
