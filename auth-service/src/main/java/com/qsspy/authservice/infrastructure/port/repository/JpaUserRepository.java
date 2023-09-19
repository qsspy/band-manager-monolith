package com.qsspy.authservice.infrastructure.port.repository;

import com.qsspy.authservice.application.authorizer.port.input.UserContext;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
interface JpaUserRepository extends JpaRepository<UserEntity, UUID> {

    @Query("""
           SELECT new com.qsspy.authservice.application.authorizer.port.input.UserContext(
                u.id,
                u.email,
                u.firstName,
                u.lastName
           )
           FROM USERS u
           WHERE u.id = :id
           """)
    Optional<UserContext> findUserContextById(final UUID id);

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
