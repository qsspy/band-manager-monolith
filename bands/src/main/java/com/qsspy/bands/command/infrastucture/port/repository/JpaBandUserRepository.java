package com.qsspy.bands.command.infrastucture.port.repository;

import com.qsspy.jpadatamodel.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
interface JpaBandUserRepository extends JpaRepository<UserEntity, UUID> {
}
