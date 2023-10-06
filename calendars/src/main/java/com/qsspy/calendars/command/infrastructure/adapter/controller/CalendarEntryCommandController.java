package com.qsspy.calendars.command.infrastructure.adapter.controller;

import com.qsspy.authservice.application.authorizer.port.input.AuthInterceptor;
import com.qsspy.authservice.application.authorizer.port.input.UserContext;
import com.qsspy.calendars.command.application.entry.create.port.input.CreateCalendarEntryCommandHandler;
import com.qsspy.calendars.command.application.entry.edit.port.input.EditCalendarEntryCommandHandler;
import com.qsspy.calendars.command.application.entry.remove.port.input.RemoveCalendarEntryCommandHandler;
import com.qsspy.calendars.command.application.entry.restriction.port.input.RestrictMemberPrivilegesForEntryCommandHandler;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/bands/{bandId}")
@RequiredArgsConstructor
@Slf4j
class CalendarEntryCommandController {

    private final AuthInterceptor authInterceptor;
    private final CreateCalendarEntryCommandHandler createCalendarEntryCommandHandler;
    private final RemoveCalendarEntryCommandHandler removeCalendarEntryCommandHandler;
    private final EditCalendarEntryCommandHandler editCalendarEntryCommandHandler;
    private final RestrictMemberPrivilegesForEntryCommandHandler restrictMemberPrivilegesForEntryCommandHandler;

    @PostMapping("/calendar/entries")
    ResponseEntity<Object> addCalendarEntry(
            @RequestHeader(HttpHeaders.AUTHORIZATION) final String token,
            @PathVariable("bandId") final UUID bandId,
            @RequestBody final CalendarEntryRequestBody request
    ) {
        return authInterceptor.withBandMemberPrivilegeRestrictedAuthorization(
                token,
                bandId,
                UserContext.Privileges::canAddCalendarEntries,
                context -> {
                    final var command = RequestToCommandMapper.toCommand(request, context, bandId);

                    try {
                        createCalendarEntryCommandHandler.handle(command);
                        return ResponseEntity.ok().build();
                    } catch (final Exception exception) {
                        log.error("An error occurred while adding calendar entry", exception);
                        return ResponseEntity.internalServerError().build();
                    }
                },
                () -> ResponseEntity.internalServerError().build()
        );
    }

    @DeleteMapping("/calendar/entries/{entryId}")
    ResponseEntity<Object> removeCalendarEntry(
            @RequestHeader(HttpHeaders.AUTHORIZATION) final String token,
            @PathVariable("bandId") final UUID bandId,
            @PathVariable("entryId") final UUID entryId
    ) {
        return authInterceptor.withBandMemberPrivilegeRestrictedAuthorization(
                token,
                bandId,
                UserContext.Privileges::canDeleteCalendarEntries,
                context -> {
                    final var command = RequestToCommandMapper.toCommand(context, bandId, entryId);

                    try {
                        removeCalendarEntryCommandHandler.handle(command);
                        return ResponseEntity.ok().build();
                    } catch (final Exception exception) {
                        log.error("An error occurred while removing calendar entry", exception);
                        return ResponseEntity.internalServerError().build();
                    }
                },
                () -> ResponseEntity.internalServerError().build()
        );
    }

    @PutMapping("/calendar/entries/{entryId}")
    ResponseEntity<Object> editCalendarEntry(
            @RequestHeader(HttpHeaders.AUTHORIZATION) final String token,
            @PathVariable("bandId") final UUID bandId,
            @PathVariable("entryId") final UUID entryId,
            @RequestBody final CalendarEntryRequestBody request
    ) {
        return authInterceptor.withBandMemberPrivilegeRestrictedAuthorization(
                token,
                bandId,
                UserContext.Privileges::canEditCalendarEntries,
                context -> {
                    final var command = RequestToCommandMapper.toCommand(request, context, bandId, entryId);

                    try {
                        editCalendarEntryCommandHandler.handle(command);
                        return ResponseEntity.ok().build();
                    } catch (final Exception exception) {
                        log.error("An error occurred while editing calendar entry", exception);
                        return ResponseEntity.internalServerError().build();
                    }
                },
                () -> ResponseEntity.internalServerError().build()
        );
    }

    @PutMapping("/members/{memberId}/calendar/entries/{entryId}/privileges")
    ResponseEntity<Object> editCalendarEntryRestrictedMemberPrivileges(
            @RequestHeader(HttpHeaders.AUTHORIZATION) final String token,
            @PathVariable("bandId") final UUID bandId,
            @PathVariable("memberId") final UUID memberId,
            @PathVariable("entryId") final UUID entryId,
            @RequestBody final RestrictMemberPrivilegesForEntryRequestBody request
    ) {
        return authInterceptor.withBandAdminAuthorization(
                token,
                bandId,
                context -> {
                    final var command = RequestToCommandMapper.toCommand(request, context, bandId, entryId, memberId);

                    try {
                        restrictMemberPrivilegesForEntryCommandHandler.handle(command);
                        return ResponseEntity.ok().build();
                    } catch (final Exception exception) {
                        log.error("An error occurred while editing calendar entry privileges for member", exception);
                        return ResponseEntity.internalServerError().build();
                    }
                },
                () -> ResponseEntity.internalServerError().build()
        );
    }
}
