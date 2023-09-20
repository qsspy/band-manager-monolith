package com.qsspy.finances.command.infrastructure.adapter.controller;

import com.qsspy.authservice.application.authorizer.port.input.AuthInterceptor;
import com.qsspy.authservice.application.authorizer.port.input.UserContext;
import com.qsspy.finances.command.application.addition.port.input.AddFinanceEntryCommandHandler;
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
class FinanceEntryController {

    private final AuthInterceptor authInterceptor;
    private final AddFinanceEntryCommandHandler addFinanceEntryCommandHandler;

    @PostMapping("/finances/entries")
    ResponseEntity<Object> addFinanceEntry(
            @RequestHeader(HttpHeaders.AUTHORIZATION) final String token,
            @PathVariable("bandId") final UUID bandId,
            @RequestBody final AddFinanceEntryRequestBody request
    ) {
        return authInterceptor.withBandMemberPrivilegeRestrictedAuthorization(
                token,
                bandId,
                UserContext.Privileges::canAddFinanceEntries,
                context -> {
                    final var command = RequestToCommandMapper.toCommand(request, context, bandId);

                    try {
                        addFinanceEntryCommandHandler.handle(command);
                        return ResponseEntity.ok().build();
                    } catch (final Exception exception) {
                        log.error("An error occurred while adding finance entry", exception);
                        return ResponseEntity.internalServerError().build();
                    }
                },
                () -> ResponseEntity.internalServerError().build()
        );
    }
}
