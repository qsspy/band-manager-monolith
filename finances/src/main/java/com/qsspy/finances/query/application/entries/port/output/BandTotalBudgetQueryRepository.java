package com.qsspy.finances.query.application.entries.port.output;

import java.math.BigDecimal;
import java.util.UUID;

public interface BandTotalBudgetQueryRepository {

    /**
     * Retrieves total budget of the band
     *
     * @param bandId id of the band
     * @return total budget
     */
    BigDecimal getBandTotalBudget(final UUID bandId);
}
