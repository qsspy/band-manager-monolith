package com.qsspy.authservice.infrastructure.adapter.controller;

import com.qsspy.authservice.application.register.port.input.RegisterCommand;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
final class RequestToCommandMapper {

    static RegisterCommand toCommand(final RegisterRequestBody body) {
        return RegisterCommand.builder()
                .email(body.email())
                .password(body.password())
                .firstName(body.firstName())
                .lastName(body.lastName())
                .build();
    }
}
