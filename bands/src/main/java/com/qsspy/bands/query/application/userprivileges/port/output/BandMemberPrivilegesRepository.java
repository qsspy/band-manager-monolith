package com.qsspy.bands.query.application.userprivileges.port.output;

import com.qsspy.bands.query.application.userprivileges.port.output.dto.BandMemberPrivilegesDTO;

import java.util.Optional;
import java.util.UUID;

public interface BandMemberPrivilegesRepository {

    /**
     * Returns band default privileges by id
     *
     * @param bandId id of the band
     * @param memberId id of the band member
     * @return default privileges
     */
    Optional<BandMemberPrivilegesDTO> finBandMemberPrivileges(final UUID bandId, final UUID memberId);
}
