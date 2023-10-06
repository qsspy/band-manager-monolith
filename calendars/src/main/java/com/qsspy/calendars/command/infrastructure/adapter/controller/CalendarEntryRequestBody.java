package com.qsspy.calendars.command.infrastructure.adapter.controller;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.qsspy.domain.calendar.EventKind;
import org.springframework.lang.Nullable;

import java.math.BigDecimal;
import java.time.LocalDateTime;

record CalendarEntryRequestBody(
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
        LocalDateTime eventDate,
        EventKind eventKind,
        BigDecimal amount,
        @Nullable
        String address,
        @Nullable
        Integer eventDurationHours,
        @Nullable
        String description
) { }
