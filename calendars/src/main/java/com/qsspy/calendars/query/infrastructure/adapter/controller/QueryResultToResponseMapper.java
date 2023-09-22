package com.qsspy.calendars.query.infrastructure.adapter.controller;

import com.qsspy.calendars.query.application.entries.details.port.input.GetCalendarEntryDetailsQueryResult;
import com.qsspy.calendars.query.application.entries.list.port.input.GetCalendarEntryListQueryResult;
import com.qsspy.calendars.query.application.membersrestrictions.port.input.GetCalendarEntryMemberRestrictionQueryResult;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.UUID;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
class QueryResultToResponseMapper {

    static GetCalendarEntryMemberRestrictionQueryResponse toResponse(final GetCalendarEntryMemberRestrictionQueryResult result) {
        final var resultMap = new HashMap<UUID, GetCalendarEntryMemberRestrictionQueryResponse.EntryRestriction>();

        result.entryRestrictions()
                .forEach(
                        (entryId, entryRestriction) -> {
                            final var memberMap = new HashMap<UUID, GetCalendarEntryMemberRestrictionQueryResponse.EntryRestriction.Member>();
                            entryRestriction.memberPrivileges().forEach(
                                    (memberId, member) -> {
                                        memberMap.put(
                                                memberId,
                                                GetCalendarEntryMemberRestrictionQueryResponse.EntryRestriction.Member.builder()
                                                        .memberEmail(member.memberEmail())
                                                        .canSeeCalendarEntry(member.canSeeCalendarEntry())
                                                        .canSeeCalendarEntryPayment(member.canSeeCalendarEntryPayment())
                                                        .canSeeCalendarEntryDetails(member.canSeeCalendarEntryDetails())
                                                        .build()
                                        );
                                    }
                            );

                            resultMap.put(
                                    entryId,
                                    new GetCalendarEntryMemberRestrictionQueryResponse.EntryRestriction(
                                            entryRestriction.eventKind(),
                                            entryRestriction.eventDate(),
                                            memberMap
                                    )
                            );
                        }
                );

        return new GetCalendarEntryMemberRestrictionQueryResponse(resultMap);
    }

    static GetCalendarEntryDetailsQueryResponse toResponse(final GetCalendarEntryDetailsQueryResult result) {
        return GetCalendarEntryDetailsQueryResponse.builder()
                .address(result.address())
                .description(result.description())
                .eventDurationHours(result.eventDuration() != null ? (int) result.eventDuration().get(ChronoUnit.HOURS) : null)
                .build();
    }

    static GetCalendarEntryListQueryResponse toResponse(final GetCalendarEntryListQueryResult result) {
        final var responseList = result.entries().stream()
                .map(item -> GetCalendarEntryListQueryResponse.ListItem.builder()
                        .id(item.id())
                        .eventKind(item.eventKind())
                        .eventDate(item.eventDate())
                        .amount(item.amount())
                        .canSeeDetails(item.canSeeDetails())
                        .build())
                .toList();
        return new GetCalendarEntryListQueryResponse(responseList);
    }
}
