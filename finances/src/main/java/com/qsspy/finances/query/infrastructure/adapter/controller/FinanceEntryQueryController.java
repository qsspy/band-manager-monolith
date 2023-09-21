package com.qsspy.finances.query.infrastructure.adapter.controller;

import com.qsspy.authservice.application.authorizer.port.input.AuthInterceptor;
import com.qsspy.authservice.application.authorizer.port.input.UserContext;
import com.qsspy.finances.query.application.entries.port.input.GetFinanceEntriesQuery;
import com.qsspy.finances.query.application.entries.port.input.GetFinanceEntriesQueryHandler;
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
class FinanceEntryQueryController {

    private final AuthInterceptor authInterceptor;
    private final GetFinanceEntriesQueryHandler getFinanceEntriesQueryHandler;

    @GetMapping("/finances/entries")
    ResponseEntity<Object> addFinanceEntry(
            @RequestHeader(HttpHeaders.AUTHORIZATION) final String token,
            @PathVariable("bandId") final UUID bandId
    ) {
        return authInterceptor.withBandMemberPrivilegeRestrictedAuthorization(
                token,
                bandId,
                UserContext.Privileges::canAccessFinanceHistory,
                context -> {
                    final var query = new GetFinanceEntriesQuery(bandId, context.userId());

                    try {
                        final var result = getFinanceEntriesQueryHandler.handle(query);
                        final var response = QueryResultToResponseMapper.toResponse(result);
                        return ResponseEntity.ok(response);
                    } catch (final Exception exception) {
                        log.error("An error occurred while fetching finance entries", exception);
                        return ResponseEntity.internalServerError().build();
                    }
                },
                () -> ResponseEntity.internalServerError().build()
        );
    }
}
