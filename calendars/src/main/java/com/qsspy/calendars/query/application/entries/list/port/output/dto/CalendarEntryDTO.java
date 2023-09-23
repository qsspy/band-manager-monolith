package com.qsspy.calendars.query.application.entries.list.port.output.dto;

import com.qsspy.domain.calendar.EventKind;
import org.springframework.lang.Nullable;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public record CalendarEntryDTO(
        UUID id,
        EventKind eventKind,
        LocalDateTime eventDate,
        BigDecimal amount,
        @Nullable
        Boolean canSeeDetails,
        boolean canSeeDetailsByDefault,
        @Nullable
        Boolean canSeePayment,
        boolean canSeePaymentByDefault
) { }
