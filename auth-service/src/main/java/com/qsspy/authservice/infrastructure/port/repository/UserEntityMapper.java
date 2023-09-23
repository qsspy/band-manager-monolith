package com.qsspy.authservice.infrastructure.port.repository;

import com.qsspy.authservice.application.register.port.input.RegisterCommand;
import com.qsspy.domain.user.User;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.UUID;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Slf4j
final class UserEntityMapper {

    static User mapFromCommand(final RegisterCommand command) {
        final var id = UUID.randomUUID();
        log.info("Creating user with id: {}", id);
        return User.builder()
                .id(id)
                .email(command.email())
                .password(command.password())
                .firstName(command.firstName())
                .lastName(command.lastName())
                .build();
    }
}
