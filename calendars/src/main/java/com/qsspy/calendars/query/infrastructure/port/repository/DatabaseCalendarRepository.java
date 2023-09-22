package com.qsspy.calendars.query.infrastructure.port.repository;

import com.qsspy.calendars.query.application.entries.details.port.output.CalendarEntryDetailsGetRepository;
import com.qsspy.calendars.query.application.entries.details.port.output.dto.CalendarEntryDetailsDTO;
import com.qsspy.calendars.query.application.entries.list.port.output.CalendarEntryListRepository;
import com.qsspy.calendars.query.application.entries.list.port.output.dto.CalendarEntryDTO;
import com.qsspy.calendars.query.application.membersrestrictions.port.output.CalendarEntryMemberRestrictionRepository;
import com.qsspy.calendars.query.application.membersrestrictions.port.output.dto.CalendarEntryMemberRestrictionDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
@RequiredArgsConstructor
class DatabaseCalendarRepository implements CalendarEntryMemberRestrictionRepository, CalendarEntryDetailsGetRepository, CalendarEntryListRepository {

    private final JpaCalendarQueryRepository jpaCalendarQueryRepository;

    @Override
    public List<CalendarEntryMemberRestrictionDTO> getMemberRestrictions(final UUID bandId) {
        return jpaCalendarQueryRepository.getCalendarEntriesUserRestrictions(bandId);
    }

    @Override
    public Optional<CalendarEntryDetailsDTO> getFinanceEntryDetails(final UUID bandId, final UUID memberId, final UUID entryId) {
        return jpaCalendarQueryRepository.findCalendarEntryDetailsByBandIdAndMemberIdAndEntryId(bandId, memberId, entryId);
    }

    @Override
    public List<CalendarEntryDTO> getCalendarEntries(final UUID bandId, final UUID memberId) {
        return jpaCalendarQueryRepository.getCalendarEntriesByBandIdAndMemberId(bandId, memberId);
    }
}
