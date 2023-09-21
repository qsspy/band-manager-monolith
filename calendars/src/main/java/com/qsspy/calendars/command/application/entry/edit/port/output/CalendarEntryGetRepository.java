package com.qsspy.calendars.command.application.entry.edit.port.output;

import com.qsspy.calendars.command.domain.entry.CalendarEntry;

import java.util.Optional;
import java.util.UUID;

public interface CalendarEntryGetRepository {

    /**
     * Fetches calendar entry from repository
     *
     * @param entryId entry id to be fetched
     * @return calendar entry is exists
     */
    Optional<CalendarEntry> findById(final UUID entryId);
}
