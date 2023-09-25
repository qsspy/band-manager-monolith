package com.qsspy.finances.query.infrastructure.repository;

import com.qsspy.domain.finances.FinanceEntry;
import com.qsspy.finances.query.application.entries.port.output.dto.FinanceEntryDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Repository
interface JpaFinanceEntryQueryRepository extends JpaRepository<FinanceEntry, UUID> {

    @Query("""
           SELECT SUM(f.amount.value)
           FROM FINANCE_ENTRIES f
           WHERE f.bandId.value = :bandId
           """)
    BigDecimal getSumOfFinanceEntriesAmounts(final UUID bandId);

    @Query("""
           SELECT new com.qsspy.finances.query.application.entries.port.output.dto.FinanceEntryDTO(
                FE.id.value,
                FE.amount.value,
                FE.description.text,
                FE.initiator.email,
                FE.isOutcome
           )
           FROM FINANCE_ENTRIES FE
           INNER JOIN BANDS B ON B.id.value = FE.bandId.value
           LEFT JOIN USERS U1 ON U1.id = B.adminUser.id
           LEFT JOIN USERS U2 ON U2.memberBand.id.value = B.id.value
           INNER JOIN DEFAULT_BAND_PRIVILEGES DP ON DP.id.value = B.id.value
           WHERE B.id.value = :bandId
               AND
                    (
                        U1.id = :memberId
                        OR
                        (
                           U2.id = :memberId
                           AND
                           CASE
                                WHEN FE.isOutcome THEN DP.canSeeFinanceOutcomeEntries.isAllowed
                                ELSE DP.canSeeFinanceIncomeEntries.isAllowed
                           END
                        )
                    )
           """)
    List<FinanceEntryDTO> getFinanceEntriesInContextOfMemberPrivileges(final UUID bandId, final UUID memberId);
}
