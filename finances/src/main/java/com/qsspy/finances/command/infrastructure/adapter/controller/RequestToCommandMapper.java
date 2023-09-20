package com.qsspy.finances.command.infrastructure.adapter.controller;

import com.qsspy.authservice.application.authorizer.port.input.UserContext;
import com.qsspy.finances.command.application.addition.port.input.AddFinanceEntryCommand;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.UUID;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
final class RequestToCommandMapper {

    static AddFinanceEntryCommand toCommand(
            final AddFinanceEntryRequestBody request,
            final UserContext userContext,
            final UUID bandId
            ) {
        return AddFinanceEntryCommand.builder()
                .initiatorsBandId(userContext.userMembershipBandId())
                .bandId(bandId)
                .amount(request.amount())
                .description(request.description())
                .initiatorEmail(userContext.email())
                .isOutcome(request.isOutcome())
                .build();
    }
}
