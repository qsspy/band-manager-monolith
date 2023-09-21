package com.qsspy.finances.query.infrastructure.adapter.controller;

import com.qsspy.finances.query.application.entries.port.input.GetFinanceEntriesQueryResult;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
final class QueryResultToResponseMapper {

    static GetFinanceEntriesQueryResponse toResponse(final GetFinanceEntriesQueryResult result) {
        return GetFinanceEntriesQueryResponse.builder()
                .entries(mapEntries(result.entries()))
                .totalBudget(result.totalBudget())
                .build();
    }

    private static List<GetFinanceEntriesQueryResponse.FinanceEntry> mapEntries(final List<GetFinanceEntriesQueryResult.FinanceEntry> entries) {
        return entries.stream()
                .map(entry ->
                        GetFinanceEntriesQueryResponse.FinanceEntry.builder()
                                .entryId(entry.entryId())
                                .amount(entry.amount())
                                .description(entry.description())
                                .initiatorEmail(entry.initiatorEmail())
                                .isOutcome(entry.isOutcome())
                                .build())
                .toList();
    }
}
