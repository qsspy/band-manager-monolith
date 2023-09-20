package com.qsspy.finances.command.application.addition.port.output;

import com.qsspy.finances.command.domain.entry.FinanceEntry;

public interface FinanceEntrySaveRepository {

    /**
     * Saves entry to the repository
     *
     * @param entry entry to be saved
     */
    void save(final FinanceEntry entry);
}
