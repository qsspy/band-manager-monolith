package com.qsspy.calendars.query.application.entries.details;

import com.qsspy.calendars.query.application.entries.details.port.input.GetCalendarEntryDetailsQuery;
import com.qsspy.calendars.query.application.entries.details.port.input.GetCalendarEntryDetailsQueryHandler;
import com.qsspy.calendars.query.application.entries.details.port.input.GetCalendarEntryDetailsQueryResult;
import com.qsspy.calendars.query.application.entries.details.port.output.CalendarEntryDetailsGetRepository;
import com.qsspy.calendars.query.application.entries.details.port.output.dto.CalendarEntryDetailsDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
class GetCalendarEntryDetailsQueryHandlerImpl implements GetCalendarEntryDetailsQueryHandler {

    private final CalendarEntryDetailsGetRepository repository;

    @Override
    public GetCalendarEntryDetailsQueryResult handle(final GetCalendarEntryDetailsQuery query) {
        query.validate();

        return repository.getFinanceEntryDetails(query.bandId(), query.memberId(), query.entryId())
                .map(this::mapToResult)
                .orElseThrow(() -> new IllegalStateException("Could not find calendar entry details or user has no privileges to it"));
    }

    private GetCalendarEntryDetailsQueryResult mapToResult(final CalendarEntryDetailsDTO dto) {
        return GetCalendarEntryDetailsQueryResult.builder()
                .eventDuration(dto.eventDuration())
                .address(dto.address())
                .description(dto.description())
                .build();
    }
}
