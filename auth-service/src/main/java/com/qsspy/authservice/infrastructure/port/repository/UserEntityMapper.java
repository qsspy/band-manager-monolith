package com.qsspy.authservice.infrastructure.port.repository;

import com.qsspy.authservice.application.register.port.input.RegisterCommand;
import com.qsspy.jpadatamodel.entity.UserEntity;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.UUID;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
final class UserEntityMapper {

    static UserEntity mapFromCommand(final RegisterCommand command) {
        return UserEntity.builder()
                .id(UUID.randomUUID())
                .email(command.email())
                .password(command.password())
                .firstName(command.firstName())
                .lastName(command.lastName())
                .build();
    }
}
