package com.qsspy.bands.query.infrastructure.port.repository;

import com.qsspy.bands.query.application.members.port.output.dto.BandMemberDTO;
import com.qsspy.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
interface JpaUserQueryRepository extends JpaRepository<User, UUID> {

    @Query("""
           SELECT new com.qsspy.bands.query.application.members.port.output.dto.BandMemberDTO(
                u.id,
                u.email,
                u.firstName,
                u.lastName
           )
           FROM USERS u
           INNER JOIN BANDS b ON u.memberBand.id.value = b.id.value
           WHERE
                b.id.value = :id
           """)
    List<BandMemberDTO> findBandMembersById(final UUID id);
}
