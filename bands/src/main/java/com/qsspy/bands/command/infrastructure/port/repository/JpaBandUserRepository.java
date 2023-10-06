package com.qsspy.bands.command.infrastructure.port.repository;

import com.qsspy.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
interface JpaBandUserRepository extends JpaRepository<User, UUID> {

    Optional<User> findByEmail(final String email);
}
