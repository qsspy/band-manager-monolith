package com.qsspy.calendars.query.application.entries.details.port.output;

import com.qsspy.calendars.query.application.entries.details.port.output.dto.CalendarEntryDetailsDTO;

import java.util.Optional;
import java.util.UUID;

public interface CalendarEntryDetailsGetRepository {

    /**
     * Fetches calendar entry details from repository
     *
     * @param bandId id of the band
     * @param memberId id of the band member used for privilege verification
     * @param entryId id of the calendar entry
     * @return calendar entry details if exists
     */
   Optional<CalendarEntryDetailsDTO> getFinanceEntryDetails(final UUID bandId, final UUID memberId, final UUID entryId);
}
