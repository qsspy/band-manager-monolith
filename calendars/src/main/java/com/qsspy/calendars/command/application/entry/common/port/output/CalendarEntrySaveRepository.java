package com.qsspy.calendars.command.application.entry.common.port.output;

import com.qsspy.calendars.command.domain.entry.CalendarEntry;

public interface CalendarEntrySaveRepository {

    /**
     * Saves entry to the repository
     *
     * @param entry entry to be saved
     */
    void save(final CalendarEntry entry);
}
