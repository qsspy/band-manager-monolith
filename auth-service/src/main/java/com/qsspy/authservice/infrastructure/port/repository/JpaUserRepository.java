package com.qsspy.authservice.infrastructure.port.repository;

import com.qsspy.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
interface JpaUserRepository extends JpaRepository<User, UUID> {

    @Query("""
           SELECT new com.qsspy.authservice.infrastructure.port.repository.UserDataDto(
                u.id,
                u.email,
                u.firstName,
                u.lastName,
                u.memberBand.id,
                u.ownedBand.id
           )
           FROM USERS u
           WHERE u.id = :id
           """)
    Optional<UserDataDto> findUserContextById(final UUID id);

    @Query("""
           SELECT u.id
           FROM USERS u
           WHERE
                u.email = :email
                AND u.password = :password
           """)
    Optional<UUID> findUserIdByEmailAndPassword(final String email, final String password);

    boolean existsByEmail(final String email);
}
