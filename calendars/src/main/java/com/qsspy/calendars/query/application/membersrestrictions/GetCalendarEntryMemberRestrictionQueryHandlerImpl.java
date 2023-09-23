package com.qsspy.calendars.query.application.membersrestrictions;

import com.qsspy.calendars.query.application.membersrestrictions.port.input.GetCalendarEntryMemberRestrictionQuery;
import com.qsspy.calendars.query.application.membersrestrictions.port.input.GetCalendarEntryMemberRestrictionQueryHandler;
import com.qsspy.calendars.query.application.membersrestrictions.port.input.GetCalendarEntryMemberRestrictionQueryResult;
import com.qsspy.calendars.query.application.membersrestrictions.port.output.CalendarEntryMemberRestrictionRepository;
import com.qsspy.calendars.query.application.membersrestrictions.port.output.dto.CalendarEntryMemberRestrictionDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
class GetCalendarEntryMemberRestrictionQueryHandlerImpl implements GetCalendarEntryMemberRestrictionQueryHandler {

    private final CalendarEntryMemberRestrictionRepository repository;

    @Override
    public GetCalendarEntryMemberRestrictionQueryResult handle(final GetCalendarEntryMemberRestrictionQuery query) {
        query.validate();

        final var restrictionDTOs = repository.getMemberRestrictions(query.bandId());
        final var entryRestrictions = parseToEntriesMap(restrictionDTOs);
        return new GetCalendarEntryMemberRestrictionQueryResult(entryRestrictions);
    }

    private Map<UUID, GetCalendarEntryMemberRestrictionQueryResult.EntryRestriction> parseToEntriesMap(final List<CalendarEntryMemberRestrictionDTO> DTOs) {
        final Map<UUID, GetCalendarEntryMemberRestrictionQueryResult.EntryRestriction> entryRestrictions = new HashMap<>();
        for(final CalendarEntryMemberRestrictionDTO dto : DTOs){

            final var member = GetCalendarEntryMemberRestrictionQueryResult.EntryRestriction.Member.builder()
                    .memberEmail(dto.memberEmail())
                    .canSeeCalendarEntry(dto.canSeeCalendarEntry())
                    .canSeeCalendarEntryPayment(dto.canSeeCalendarEntryPayment())
                    .canSeeCalendarEntryDetails(dto.canSeeCalendarEntryDetails())
                    .build();

            Optional.ofNullable(entryRestrictions.get(dto.entryId()))
                    .ifPresentOrElse(
                            entryRestriction -> entryRestriction.memberPrivileges().put(dto.memberId(), member),
                            () -> {
                                final var memberMap = new HashMap<UUID, GetCalendarEntryMemberRestrictionQueryResult.EntryRestriction.Member>();
                                memberMap.put(dto.memberId(), member);
                                entryRestrictions.put(
                                        dto.entryId(),
                                        new GetCalendarEntryMemberRestrictionQueryResult.EntryRestriction(
                                                dto.eventKind(),
                                                dto.eventDate(),
                                                memberMap
                                        )
                                );
                            }
                    );
        }

        return entryRestrictions;
    }
}
