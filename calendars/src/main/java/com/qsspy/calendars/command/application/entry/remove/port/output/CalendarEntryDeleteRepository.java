package com.qsspy.calendars.command.application.entry.remove.port.output;

import java.util.UUID;

public interface CalendarEntryDeleteRepository {

    /**
     * Removes calendar entry from repository
     *
     * @param bandId band id that owns the entry
     * @param entryId entry id to be removed
     */
    void remove(final UUID bandId, final UUID entryId);
}
