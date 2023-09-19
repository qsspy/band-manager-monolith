package com.qsspy.bands.command.application.creation.port.output;

import com.qsspy.bands.command.domain.band.Band;

public interface BandSaveRepository {

    /**
     * Saves band in repository
     *
     * @param band band to be saved
     */
    void save(final Band band);
}
