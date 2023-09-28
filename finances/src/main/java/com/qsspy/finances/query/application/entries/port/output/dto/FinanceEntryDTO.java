package com.qsspy.finances.query.application.entries.port.output.dto;

import org.springframework.lang.Nullable;

import java.math.BigDecimal;
import java.util.UUID;

public record FinanceEntryDTO(
        UUID entryId,
        BigDecimal amount,
        @Nullable
        String description,
        String initiatorEmail,
        boolean isOutcome
) {}
