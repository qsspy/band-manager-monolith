package com.qsspy.authservice.infrastructure.port.repository;

import com.qsspy.domain.band.BandMemberPrivileges;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
interface JpaUserBandPrivilegesRepository extends JpaRepository<BandMemberPrivileges, UUID> {

    @Query("""
           SELECT new com.qsspy.authservice.infrastructure.port.repository.UserBandPrivilegesDto(
                p.canAddFinanceEntries.isAllowed,
                p.canAddCalendarEntries.isAllowed,
                p.canEditCalendarEntries.isAllowed,
                p.canDeleteCalendarEntries.isAllowed,
                p.canAccessFinanceHistory.isAllowed,
                p.canAccessCalendar.isAllowed
           )
           FROM BAND_MEMBER_PRIVILEGES p
           WHERE
                p.id.bandId = :bandId
                AND p.id.memberId = :userId
           """)
    UserBandPrivilegesDto findUserBandPrivileges(final UUID userId, final UUID bandId);
}