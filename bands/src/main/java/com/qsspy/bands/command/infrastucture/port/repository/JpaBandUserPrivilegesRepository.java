package com.qsspy.bands.command.infrastucture.port.repository;

import com.qsspy.bands.command.application.member.addition.port.output.dto.UserMembership;
import com.qsspy.jpadatamodel.entity.BandMemberPrivilegesEntity;
import com.qsspy.jpadatamodel.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
interface JpaBandUserPrivilegesRepository extends JpaRepository<BandMemberPrivilegesEntity, UUID> {

    void deleteByBandId(final UUID bandId);
}
