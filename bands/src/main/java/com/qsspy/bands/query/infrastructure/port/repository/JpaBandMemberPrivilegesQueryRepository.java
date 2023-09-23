package com.qsspy.bands.query.infrastructure.port.repository;

import com.qsspy.bands.query.application.userprivileges.port.output.dto.BandMemberPrivilegesDTO;
import com.qsspy.domain.band.BandMemberPrivileges;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
interface JpaBandMemberPrivilegesQueryRepository extends JpaRepository<BandMemberPrivileges, UUID> {

    @Query("""
           SELECT new com.qsspy.bands.query.application.userprivileges.port.output.dto.BandMemberPrivilegesDTO(
                p.canAccessCalendar.isAllowed,
                p.canAddCalendarEntries.isAllowed,
                p.canEditCalendarEntries.isAllowed,
                p.canDeleteCalendarEntries.isAllowed,
                
                p.canAccessFinanceHistory.isAllowed,
                p.canAddFinanceEntries.isAllowed,
                
                p.canSeeFinanceIncomeEntries.isAllowed,
                p.canSeeFinanceOutcomeEntries.isAllowed
           )
           FROM BAND_MEMBER_PRIVILEGES p
           WHERE
                p.id.memberId = :memberId
                AND p.id.bandId = :bandId
           """)
    Optional<BandMemberPrivilegesDTO> findBandMemberPrivileges(final UUID bandId, final UUID memberId);
}
