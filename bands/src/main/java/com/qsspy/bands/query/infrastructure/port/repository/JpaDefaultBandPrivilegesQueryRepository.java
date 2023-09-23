package com.qsspy.bands.query.infrastructure.port.repository;

import com.qsspy.bands.query.application.defaultprivileges.port.output.dto.BandDefaultPrivilegesDTO;
import com.qsspy.domain.band.BandMemberPrivileges;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
interface JpaDefaultBandPrivilegesQueryRepository extends JpaRepository<BandMemberPrivileges, UUID> {

    @Query("""
           SELECT new com.qsspy.bands.query.application.defaultprivileges.port.output.dto.BandDefaultPrivilegesDTO(
                p.canAccessCalendar.isAllowed,
                p.canAddCalendarEntries.isAllowed,
                p.canEditCalendarEntries.isAllowed,
                p.canDeleteCalendarEntries.isAllowed,
                
                p.canAccessFinanceHistory.isAllowed,
                p.canAddFinanceEntries.isAllowed,
                
                p.canSeeFinanceIncomeEntries.isAllowed,
                p.canSeeFinanceOutcomeEntries.isAllowed,
                
                p.canSeeCalendarEntryByDefault.isAllowed,
                p.canSeeCalendarEntryPaymentByDefault.isAllowed,
                p.canSeeCalendarEntryDetailsByDefault.isAllowed
           )
           FROM DEFAULT_BAND_PRIVILEGES p
           WHERE
                p.id.value = :id
           """)
    Optional<BandDefaultPrivilegesDTO> findBandDefaultPrivileges(final UUID id);
}
