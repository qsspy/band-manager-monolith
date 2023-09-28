package com.qsspy.finances.command.infrastructure.adapter.controller;

import org.springframework.lang.Nullable;

import java.math.BigDecimal;

record AddFinanceEntryRequestBody(
        BigDecimal amount,
        @Nullable
        String description,
        boolean isOutcome
) {}
