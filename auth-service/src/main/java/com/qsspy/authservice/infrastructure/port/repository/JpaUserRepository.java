package com.qsspy.authservice.infrastructure.port.repository;

import com.qsspy.authservice.application.login.port.output.UserLoginDTO;
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
                b2.id.value,
                b1.id.value
           )
           FROM USERS u
           LEFT JOIN BANDS b1 ON b1.adminUser.id = u.id
           LEFT JOIN BANDS b2 ON u.memberBand.id.value = b2.id.value
           WHERE u.id = :id
           """)
    Optional<UserDataDto> findUserContextById(final UUID id);

    @Query("""
           SELECT new com.qsspy.authservice.application.login.port.output.UserLoginDTO(
                u.id,
                b1.id.value,
                b2.id.value
           )
           FROM USERS u
           LEFT JOIN BANDS b1 ON b1.adminUser.id = u.id
           LEFT JOIN BANDS b2 ON u.memberBand.id.value = b2.id.value
           WHERE
                u.email = :email
                AND u.password = :password
           """)
    Optional<UserLoginDTO> findUserIdByEmailAndPassword(final String email, final String password);

    boolean existsByEmail(final String email);
}
