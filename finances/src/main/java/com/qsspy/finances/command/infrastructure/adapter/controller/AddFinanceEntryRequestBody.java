package com.qsspy.finances.command.infrastructure.adapter.controller;

import com.qsspy.commons.architecture.cqrs.Command;
import com.qsspy.commons.architecture.cqrs.CommandValidationException;
import org.springframework.lang.Nullable;

import java.math.BigDecimal;
import java.util.UUID;

record AddFinanceEntryRequestBody(
        BigDecimal amount,
        @Nullable
        String description,
        boolean isOutcome
) {}
