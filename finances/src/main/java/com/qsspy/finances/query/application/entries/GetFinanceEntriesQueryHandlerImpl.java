package com.qsspy.finances.query.application.entries;

import com.qsspy.finances.query.application.entries.port.input.GetFinanceEntriesQuery;
import com.qsspy.finances.query.application.entries.port.input.GetFinanceEntriesQueryHandler;
import com.qsspy.finances.query.application.entries.port.input.GetFinanceEntriesQueryResult;
import com.qsspy.finances.query.application.entries.port.output.BandTotalBudgetQueryRepository;
import com.qsspy.finances.query.application.entries.port.output.FinanceEntryQueryRepository;
import com.qsspy.finances.query.application.entries.port.output.dto.FinanceEntryDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
class GetFinanceEntriesQueryHandlerImpl implements GetFinanceEntriesQueryHandler {

    private final FinanceEntryQueryRepository financeEntryQueryRepository;
    private final BandTotalBudgetQueryRepository bandTotalBudgetQueryRepository;


    @Override
    public GetFinanceEntriesQueryResult handle(final GetFinanceEntriesQuery query) {
        query.validate();

        final var entries = financeEntryQueryRepository.getFinanceEntries(query.bandId(), query.memberId());
        final var totalBudget = bandTotalBudgetQueryRepository.getBandTotalBudget(query.bandId());
        return mapToResult(entries, totalBudget);
    }

    private GetFinanceEntriesQueryResult mapToResult(final List<FinanceEntryDTO> entries, final BigDecimal totalBudget) {
        return new GetFinanceEntriesQueryResult(
                entries.stream()
                        .map(entry ->
                                GetFinanceEntriesQueryResult.FinanceEntry.builder()
                                        .entryId(entry.entryId())
                                        .amount(entry.amount())
                                        .description(entry.description())
                                        .initiatorEmail(entry.initiatorEmail())
                                        .isOutcome(entry.isOutcome())
                                        .build())
                        .toList(),
                totalBudget
        );
    }
}
