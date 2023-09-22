package com.qsspy.domain.finances.dto;

import lombok.Builder;
import org.springframework.lang.Nullable;

import java.math.BigDecimal;
import java.util.UUID;

@Builder
public record FinanceEntrySpecification(
        UUID bandId,
        BigDecimal amount,
        @Nullable
        String description,
        String initiatorEmail,
        boolean isOutcome
) { }
