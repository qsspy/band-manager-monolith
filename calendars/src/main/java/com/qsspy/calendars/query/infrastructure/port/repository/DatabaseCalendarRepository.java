package com.qsspy.calendars.query.infrastructure.port.repository;

import com.qsspy.calendars.query.application.membersrestrictions.port.output.CalendarEntryMemberRestrictionRepository;
import com.qsspy.calendars.query.application.membersrestrictions.port.output.dto.CalendarEntryMemberRestrictionDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
@RequiredArgsConstructor
class DatabaseCalendarRepository implements CalendarEntryMemberRestrictionRepository {

    private final JpaCalendarQueryRepository jpaCalendarQueryRepository;

    @Override
    public List<CalendarEntryMemberRestrictionDTO> getMemberRestrictions(final UUID bandId) {
        return jpaCalendarQueryRepository.getCalendarEntriesUserRestrictions(bandId);
    }
}
