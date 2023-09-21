package com.qsspy.finances.query.application.entries.port.output;

import com.qsspy.finances.query.application.entries.port.output.dto.FinanceEntryDTO;

import java.util.List;
import java.util.UUID;

public interface FinanceEntryQueryRepository {

    /**
     * Fetches finance entries for member in context of his band
     *
     * @param bandId id of the band
     * @param memberId id of the band member
     * @return list of finance entries
     */
    List<FinanceEntryDTO> getFinanceEntries(final UUID bandId, final UUID memberId);
}
