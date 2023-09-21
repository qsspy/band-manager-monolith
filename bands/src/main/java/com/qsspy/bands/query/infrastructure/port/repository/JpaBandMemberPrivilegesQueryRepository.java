package com.qsspy.bands.query.infrastructure.port.repository;

import com.qsspy.bands.query.application.defaultprivileges.port.output.dto.BandDefaultPrivilegesDTO;
import com.qsspy.bands.query.application.userprivileges.port.output.dto.BandMemberPrivilegesDTO;
import com.qsspy.jpadatamodel.entity.BandMemberPrivilegesEntity;
import com.qsspy.jpadatamodel.entity.DefaultBandPrivilegesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
interface JpaBandMemberPrivilegesQueryRepository extends JpaRepository<BandMemberPrivilegesEntity, UUID> {

    @Query("""
           SELECT new com.qsspy.bands.query.application.userprivileges.port.output.dto.BandMemberPrivilegesDTO(
                p.canAccessCalendar,
                p.canAddCalendarEntries,
                p.canEditCalendarEntries,
                p.canDeleteCalendarEntries,
                
                p.canAccessFinanceHistory,
                p.canAddFinanceEntries,
                
                p.canSeeFinanceIncomeEntries,
                p.canSeeFinanceOutcomeEntries
           )
           FROM BAND_MEMBER_PRIVILEGES p
           WHERE
                p.memberId = :memberId
                AND p.bandId = :bandId
           """)
    Optional<BandMemberPrivilegesDTO> findBandMemberPrivileges(final UUID bandId, final UUID memberId);
}
