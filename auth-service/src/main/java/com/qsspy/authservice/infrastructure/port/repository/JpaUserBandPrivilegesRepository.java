package com.qsspy.authservice.infrastructure.port.repository;

import com.qsspy.authservice.application.authorizer.port.input.UserContext;
import com.qsspy.jpadatamodel.entity.BandMemberPrivilegesEntity;
import com.qsspy.jpadatamodel.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
interface JpaUserBandPrivilegesRepository extends JpaRepository<BandMemberPrivilegesEntity, UUID> {

    @Query("""
           SELECT new com.qsspy.authservice.infrastructure.port.repository.UserBandPrivilegesDto(
                p.canAddFinanceEntries,
                p.canAddCalendarEntries,
                p.canEditCalendarEntries,
                p.canDeleteCalendarEntries
           )
           FROM BAND_MEMBER_PRIVILEGES p
           WHERE
                p.bandId = :bandId
                AND p.memberId = :userId
           """)
    UserBandPrivilegesDto findUserBandPrivileges(final UUID userId, final UUID bandId);
}
