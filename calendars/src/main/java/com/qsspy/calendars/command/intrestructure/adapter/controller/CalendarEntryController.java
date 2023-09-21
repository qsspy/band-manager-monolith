package com.qsspy.calendars.command.intrestructure.adapter.controller;

import com.qsspy.authservice.application.authorizer.port.input.AuthInterceptor;
import com.qsspy.authservice.application.authorizer.port.input.UserContext;
import com.qsspy.calendars.command.application.entry.create.port.input.CreateCalendarEntryCommandHandler;
import com.qsspy.calendars.command.application.entry.remove.port.input.RemoveCalendarEntryCommandHandler;
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
class CalendarEntryController {

    private final AuthInterceptor authInterceptor;
    private final CreateCalendarEntryCommandHandler createCalendarEntryCommandHandler;
    private final RemoveCalendarEntryCommandHandler removeCalendarEntryCommandHandler;

    @PostMapping("/calendar/entries")
    ResponseEntity<Object> addFinanceEntry(
            @RequestHeader(HttpHeaders.AUTHORIZATION) final String token,
            @PathVariable("bandId") final UUID bandId,
            @RequestBody final CreateCalendarEntryRequestBody request
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
    ResponseEntity<Object> addFinanceEntry(
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
}
