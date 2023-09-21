package com.qsspy.bands.query.infrastructure.port.repository;

import com.qsspy.bands.query.application.defaultprivileges.port.output.dto.BandDefaultPrivilegesDTO;
import com.qsspy.bands.query.application.members.port.output.dto.BandMemberDTO;
import com.qsspy.jpadatamodel.entity.UserEntity;
import org.hibernate.type.descriptor.converter.spi.JpaAttributeConverter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
interface JpaUserQueryRepository extends JpaRepository<UserEntity, UUID> {

    @Query("""
           SELECT new com.qsspy.bands.query.application.members.port.output.dto.BandMemberDTO(
                u.id,
                u.email,
                u.firstName,
                u.lastName
           )
           FROM USERS u
           INNER JOIN BANDS b ON u.memberBand.id = b.id
           WHERE
                b.id = :id
           """)
    List<BandMemberDTO> findBandMembersById(final UUID id);
}
