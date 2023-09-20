package com.qsspy.authservice.infrastructure.port.repository;

import com.qsspy.authservice.application.register.port.input.RegisterCommand;
import com.qsspy.jpadatamodel.entity.UserEntity;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.UUID;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Slf4j
final class UserEntityMapper {

    static UserEntity mapFromCommand(final RegisterCommand command) {
        final var id = UUID.randomUUID();
        log.info("Creating user with id: {}", id);
        return UserEntity.builder()
                .id(id)
                .email(command.email())
                .password(command.password())
                .firstName(command.firstName())
                .lastName(command.lastName())
                .build();
    }
}
