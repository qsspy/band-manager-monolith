package com.qsspy.authservice.infrastructure.port.repository;

import com.qsspy.authservice.application.authorizer.port.input.UserContext;
import com.qsspy.authservice.application.authorizer.port.output.UserContextRepository;
import com.qsspy.authservice.application.login.port.output.UserLoginRepository;
import com.qsspy.authservice.application.register.port.input.RegisterCommand;
import com.qsspy.authservice.application.register.port.output.UserRegisterRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
@Slf4j
class DatabaseUserRepository implements UserContextRepository, UserLoginRepository, UserRegisterRepository {

    private final JpaUserRepository jpaUserRepository;
    private final JpaUserBandPrivilegesRepository bandPrivilegesRepository;

    @Override
    public Optional<UserContext> findById(final UUID userId) {
        log.info("Finding user with id : {}", userId);
        return jpaUserRepository
                .findUserContextById(userId)
                .map(userData -> {
                    if(userData.userOwnBandId() != null) {
                        return DtoMapper.toUserContext(userData, UserBandPrivilegesDto.allAllowed());
                    }

                    if(userData.userMemberBandId() == null) {
                        return DtoMapper.toUserContext(userData, null);
                    }

                    return DtoMapper.toUserContext(
                            userData,
                            bandPrivilegesRepository.findUserBandPrivileges(userId, userData.userMemberBandId())
                    );
                });
    }

    @Override
    public Optional<UUID> getUserIdByCredentials(final String email, final String password) {
        return jpaUserRepository.findUserIdByEmailAndPassword(email, password);
    }

    @Override
    public boolean existsByEmail(final String email) {
        return jpaUserRepository.existsByEmail(email);
    }

    @Override
    public void save(final RegisterCommand command) {
        jpaUserRepository.save(
                UserEntityMapper.mapFromCommand(command)
        );
    }
}
