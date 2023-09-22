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
           WHERE f.bandId = :bandId
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
           INNER JOIN BANDS B ON FE.bandId.value = B.id
           INNER JOIN USERS U ON B.id = U.memberBand.id OR B.id = U.ownedBand.id
           WHERE
                FE.bandId.value = :bandId
                AND U.id = :memberId
                AND
                (
                    U.ownedBand.id = :bandId
                    OR
                    CASE
                        WHEN FE.isOutcome THEN
                            CASE WHEN (SELECT MP.canSeeFinanceOutcomeEntries
                                       FROM BAND_MEMBER_PRIVILEGES MP
                                       WHERE MP.bandId = B.id AND MP.memberId = U.id) IS NULL THEN (SELECT BP.canSeeFinanceOutcomeEntries
                                                                                                    FROM DEFAULT_BAND_PRIVILEGES BP
                                                                                                    WHERE BP.band.id = B.id)
                                 ELSE (SELECT MP.canSeeFinanceOutcomeEntries
                                       FROM BAND_MEMBER_PRIVILEGES MP
                                       WHERE MP.bandId = B.id AND MP.memberId = U.id)
                            END
                        ELSE
                            CASE WHEN (SELECT MP.canSeeFinanceIncomeEntries
                                       FROM BAND_MEMBER_PRIVILEGES MP
                                       WHERE MP.bandId = B.id AND MP.memberId = U.id) IS NULL THEN (SELECT BP.canSeeFinanceIncomeEntries
                                                                                                    FROM DEFAULT_BAND_PRIVILEGES BP
                                                                                                    WHERE BP.band.id = B.id)
                                 ELSE (SELECT MP.canSeeFinanceIncomeEntries
                                       FROM BAND_MEMBER_PRIVILEGES MP
                                       WHERE MP.bandId = B.id AND MP.memberId = U.id)
                            END
                    END
                )
           """)
    List<FinanceEntryDTO> getFinanceEntriesInContextOfMemberPrivileges(final UUID bandId, final UUID memberId);
}
