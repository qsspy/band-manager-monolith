package com.qsspy.bands.query.application.members.port.output;

import com.qsspy.bands.query.application.members.port.output.dto.BandMemberDTO;

import java.util.List;
import java.util.UUID;

public interface BandMembersGetRepository {

    /**
     * Fetches all members from band
     *
     * @param bandId id of the band
     * @return list of band members
     */
    List<BandMemberDTO> findBandMembers(final UUID bandId);
}
