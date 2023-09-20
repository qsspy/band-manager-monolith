package com.qsspy.bands.command.infrastucture.port.repository;

import com.qsspy.bands.command.application.member.addition.port.output.dto.UserMembership;
import com.qsspy.jpadatamodel.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
interface JpaBandUserRepository extends JpaRepository<UserEntity, UUID> {

    @Modifying
    @Query("""
           UPDATE USERS u
           SET u.memberBand.id = :bandId
           WHERE u.id = :userId
           """)
    void updateMemberBandId(final UUID userId, final UUID bandId);

    @Modifying
    @Query("""
           UPDATE USERS u
           SET u.memberBand.id = null
           WHERE u.memberBand.id = :bandId
           """)
    void removeBandMembershipForUsersInCompany(final UUID bandId);

    @Query("""
           SELECT CASE WHEN
                count(u) > 0 THEN TRUE
                ELSE FALSE
                END
           FROM USERS u
           WHERE
                u.id = :userId
                AND u.memberBand.id != null
                AND u.ownedBand.id != null
           """)
    boolean existsByOwnedBandIdOrMemberBandId(final UUID userId);

    @Query("""
           SELECT new com.qsspy.bands.command.application.member.addition.port.output.dto.UserMembership(
                u.id,
                u.ownedBand.id,
                u.memberBand.id
           )
           FROM USERS u
           WHERE
                u.email = :email
           """)
    Optional<UserMembership> getUserMembershipByEmail(final String email);
}
