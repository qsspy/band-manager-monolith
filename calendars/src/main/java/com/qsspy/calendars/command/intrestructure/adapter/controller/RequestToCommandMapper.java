package com.qsspy.calendars.command.intrestructure.adapter.controller;

import com.qsspy.authservice.application.authorizer.port.input.UserContext;
import com.qsspy.calendars.command.application.entry.create.port.input.CreateCalendarEntryCommand;
import com.qsspy.calendars.command.application.entry.edit.port.input.EditCalendarEntryCommand;
import com.qsspy.calendars.command.application.entry.remove.port.input.RemoveCalendarEntryCommand;
import com.qsspy.calendars.command.application.entry.restriction.port.input.RestrictMemberPrivilegesForEntryCommand;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.time.Duration;
import java.util.UUID;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
final class RequestToCommandMapper {

    static CreateCalendarEntryCommand toCommand(
            final CalendarEntryRequestBody request,
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

    static RemoveCalendarEntryCommand toCommand(
            final UserContext userContext,
            final UUID bandId,
            final UUID entryId
    ) {
        return RemoveCalendarEntryCommand.builder()
                .initiatorsBandId(userContext.userMembershipBandId())
                .bandId(bandId)
                .entryId(entryId)
                .build();
    }

    static EditCalendarEntryCommand toCommand(
            final CalendarEntryRequestBody request,
            final UserContext userContext,
            final UUID bandId,
            final UUID entryId
    ) {
        return EditCalendarEntryCommand.builder()
                .initiatorsBandId(userContext.userMembershipBandId())
                .bandId(bandId)
                .entryId(entryId)
                .eventDate(request.eventDate())
                .eventKind(request.eventKind())
                .amount(request.amount())
                .address(request.address())
                .eventDuration(request.eventDurationHours() != null ? Duration.ofHours(request.eventDurationHours()) : null)
                .description(request.description())
                .build();
    }

    static RestrictMemberPrivilegesForEntryCommand toCommand(
            final RestrictMemberPrivilegesForEntryRequestBody request,
            final UserContext userContext,
            final UUID bandId,
            final UUID entryId,
            final UUID memberId
    ) {
        return RestrictMemberPrivilegesForEntryCommand.builder()
                .initiatorsBandId(userContext.userMembershipBandId())
                .bandId(bandId)
                .memberId(memberId)
                .entryId(entryId)
                .canSeeCalendarEntry(request.canSeeCalendarEntry())
                .canSeeCalendarEntryPayment(request.canSeeCalendarEntryPayment())
                .canSeeCalendarEntryDetails(request.canSeeCalendarEntryDetails())
                .build();
    }
}
