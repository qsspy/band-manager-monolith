package com.qsspy.bands.command.application.common.port.output;

import com.qsspy.domain.band.Band;

public interface BandSaveRepository {

    /**
     * Saves band in repository
     *
     * @param band band to be saved
     */
    void save(final Band band);
}
