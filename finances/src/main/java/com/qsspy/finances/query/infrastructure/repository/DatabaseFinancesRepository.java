package com.qsspy.finances.query.infrastructure.repository;

import com.qsspy.finances.query.application.entries.port.output.BandTotalBudgetQueryRepository;
import com.qsspy.finances.query.application.entries.port.output.FinanceEntryQueryRepository;
import com.qsspy.finances.query.application.entries.port.output.dto.FinanceEntryDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
@RequiredArgsConstructor
class DatabaseFinancesRepository implements FinanceEntryQueryRepository, BandTotalBudgetQueryRepository {

    private final JpaFinanceEntryQueryRepository jpaFinanceEntryQueryRepository;

    @Override
    public BigDecimal getBandTotalBudget(final UUID bandId) {
        return Optional.ofNullable(jpaFinanceEntryQueryRepository.getSumOfFinanceEntriesAmounts(bandId))
                .orElse(BigDecimal.ZERO);
    }

    @Override
    public List<FinanceEntryDTO> getFinanceEntries(final UUID bandId, final UUID memberId) {
        return jpaFinanceEntryQueryRepository.getFinanceEntriesInContextOfMemberPrivileges(bandId, memberId);
    }
}
