package com.qsspy.bands.command.application.member.addtoband.port.output;

import com.qsspy.bands.command.application.member.addtoband.port.output.dto.UserMembership;

import java.util.Optional;

public interface BandUserRepository {

    /**
     * Returns user membership information
     *
     * @param userEmail email of the user
     * @return result flag
     */
    Optional<UserMembership> getUserMembershipInformation(final String userEmail);
}
