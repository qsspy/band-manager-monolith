package com.qsspy.bands.query.application.defaultprivileges.port.output;

import com.qsspy.bands.query.application.defaultprivileges.port.output.dto.BandDefaultPrivilegesDTO;

import java.util.Optional;
import java.util.UUID;

public interface BandDefaultPrivilegesRepository {

    /**
     * Returns band default privileges by id
     *
     * @param bandID id of the band
     * @return default privileges
     */
    Optional<BandDefaultPrivilegesDTO> finBandDefaultPrivileges(final UUID bandID);
}
