package com.qsspy.calendars.command.intrestructure.adapter.controller;

import com.qsspy.authservice.application.authorizer.port.input.UserContext;
import com.qsspy.calendars.command.application.entry.create.port.input.CreateCalendarEntryCommand;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.time.Duration;
import java.util.UUID;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
final class RequestToCommandMapper {

    static CreateCalendarEntryCommand toCommand(
            final CreateCalendarEntryRequestBody request,
            final UserContext userContext,
            final UUID bandId
            ) {
        return CreateCalendarEntryCommand.builder()
                .initiatorsBandId(userContext.userMembershipBandId())
                .bandId(bandId)
                .eventDate(request.eventDate())
                .eventKind(request.eventKind())
                .amount(request.amount())
                .address(request.address())
                .eventDuration(request.eventDurationHours() != null ? Duration.ofHours(request.eventDurationHours()) : null)
                .description(request.description())
                .build();
    }
}
