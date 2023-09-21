package com.qsspy.bands.query.infrastructure.port.repository;

import com.qsspy.bands.query.application.defaultprivileges.port.output.dto.BandDefaultPrivilegesDTO;
import com.qsspy.bands.query.application.members.port.output.dto.BandMemberDTO;
import com.qsspy.jpadatamodel.entity.DefaultBandPrivilegesEntity;
import com.qsspy.jpadatamodel.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
interface JpaDefaultBandPrivilegesQueryRepository extends JpaRepository<DefaultBandPrivilegesEntity, UUID> {

    @Query("""
           SELECT new com.qsspy.bands.query.application.defaultprivileges.port.output.dto.BandDefaultPrivilegesDTO(
                p.canAccessCalendar,
                p.canAddCalendarEntries,
                p.canEditCalendarEntries,
                p.canDeleteCalendarEntries,
                
                p.canAccessFinanceHistory,
                p.canAddFinanceEntries,
                
                p.canSeeFinanceIncomeEntries,
                p.canSeeFinanceOutcomeEntries,
                
                p.canSeeCalendarEntryByDefault,
                p.canSeeCalendarEntryPaymentByDefault,
                p.canSeeCalendarEntryDetailsByDefault
           )
           FROM DEFAULT_BAND_PRIVILEGES p
           WHERE
                p.id = :id
           """)
    Optional<BandDefaultPrivilegesDTO> findBandDefaultPrivileges(final UUID id);
}
