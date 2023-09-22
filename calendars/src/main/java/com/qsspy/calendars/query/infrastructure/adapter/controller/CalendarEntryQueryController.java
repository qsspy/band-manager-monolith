package com.qsspy.calendars.query.infrastructure.adapter.controller;

import com.qsspy.authservice.application.authorizer.port.input.AuthInterceptor;
import com.qsspy.authservice.application.authorizer.port.input.UserContext;
import com.qsspy.calendars.query.application.entries.details.port.input.GetCalendarEntryDetailsQuery;
import com.qsspy.calendars.query.application.entries.details.port.input.GetCalendarEntryDetailsQueryHandler;
import com.qsspy.calendars.query.application.entries.list.port.input.GetCalendarEntryListQuery;
import com.qsspy.calendars.query.application.entries.list.port.input.GetCalendarEntryListQueryHandler;
import com.qsspy.calendars.query.application.membersrestrictions.port.input.GetCalendarEntryMemberRestrictionQuery;
import com.qsspy.calendars.query.application.membersrestrictions.port.input.GetCalendarEntryMemberRestrictionQueryHandler;
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
class CalendarEntryQueryController {

    private final AuthInterceptor authInterceptor;
    private final GetCalendarEntryMemberRestrictionQueryHandler getCalendarEntryMemberRestrictionQueryHandler;
    private final GetCalendarEntryDetailsQueryHandler getCalendarEntryDetailsQueryHandler;
    private final GetCalendarEntryListQueryHandler getCalendarEntryListQueryHandler;

    @GetMapping("/calendar/entries/member-privileges")
    ResponseEntity<GetCalendarEntryMemberRestrictionQueryResponse> getCalenderEntryMemberRestrictions(
            @RequestHeader(HttpHeaders.AUTHORIZATION) final String token,
            @PathVariable("bandId") final UUID bandId
    ) {
        return authInterceptor.withBandAdminAuthorization(
                token,
                bandId,
                context -> {
                    final var query = new GetCalendarEntryMemberRestrictionQuery(bandId);

                    try {
                        final var result = getCalendarEntryMemberRestrictionQueryHandler.handle(query);
                        final var response = QueryResultToResponseMapper.toResponse(result);
                        return ResponseEntity.ok(response);
                    } catch (final Exception exception) {
                        log.error("An error occurred while fetching calendar entry member restrictions", exception);
                        return ResponseEntity.internalServerError().build();
                    }
                },
                () -> ResponseEntity.internalServerError().build()
        );
    }

    @GetMapping("/calendar/entries/{entryId}")
    ResponseEntity<GetCalendarEntryDetailsQueryResponse> getCalenderEntryDetails(
            @RequestHeader(HttpHeaders.AUTHORIZATION) final String token,
            @PathVariable("bandId") final UUID bandId,
            @PathVariable("entryId") final UUID entryId
    ) {
        return authInterceptor.withBandMemberPrivilegeRestrictedAuthorization(
                token,
                bandId,
                UserContext.Privileges::canAccessCalendar,
                context -> {
                    final var query = new GetCalendarEntryDetailsQuery(bandId, context.userId(), entryId);

                    try {
                        final var result = getCalendarEntryDetailsQueryHandler.handle(query);
                        final var response = QueryResultToResponseMapper.toResponse(result);
                        return ResponseEntity.ok(response);
                    } catch (final Exception exception) {
                        log.error("An error occurred while fetching calendar entry details", exception);
                        return ResponseEntity.internalServerError().build();
                    }
                },
                () -> ResponseEntity.internalServerError().build()
        );
    }

    @GetMapping("/calendar/entries")
    ResponseEntity<GetCalendarEntryListQueryResponse> getCalenderEntries(
            @RequestHeader(HttpHeaders.AUTHORIZATION) final String token,
            @PathVariable("bandId") final UUID bandId
    ) {
        return authInterceptor.withBandMemberPrivilegeRestrictedAuthorization(
                token,
                bandId,
                UserContext.Privileges::canAccessCalendar,
                context -> {
                    final var query = new GetCalendarEntryListQuery(bandId, context.userId());

                    try {
                        final var result = getCalendarEntryListQueryHandler.handle(query);
                        final var response = QueryResultToResponseMapper.toResponse(result);
                        return ResponseEntity.ok(response);
                    } catch (final Exception exception) {
                        log.error("An error occurred while fetching calendar entries", exception);
                        return ResponseEntity.internalServerError().build();
                    }
                },
                () -> ResponseEntity.internalServerError().build()
        );
    }
}
