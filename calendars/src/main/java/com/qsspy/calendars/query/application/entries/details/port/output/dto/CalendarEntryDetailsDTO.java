package com.qsspy.calendars.query.application.entries.details.port.output.dto;

import org.springframework.lang.Nullable;

import java.time.Duration;

public record CalendarEntryDetailsDTO(
        @Nullable
        String address,
        @Nullable
        Duration eventDuration,
        @Nullable
        String description
) { }
