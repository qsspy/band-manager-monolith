package com.qsspy.calendars.query.application.entries.list.port.output;

import com.qsspy.calendars.query.application.entries.list.port.output.dto.CalendarEntryDTO;

import java.util.List;
import java.util.UUID;

public interface CalendarEntryListRepository {

    /**
     * Fetches list of calendar entries for band member
     *
     * @param bandId id of the band
     * @param memberId id of the band member
     * @return list of calendar entries
     */
    List<CalendarEntryDTO> getCalendarEntries(final UUID bandId, final UUID memberId);
}
