package com.qsspy.bands.command.application.common.port.output;

import com.qsspy.domain.user.User;

import java.util.Optional;
import java.util.UUID;

public interface BandUserGetRepository {

    /**
     * Finds user by id
     *
     * @param userId id of the user
     * @return user
     */
    Optional<User> findById(final UUID userId);

    /**
     * Finds user by email
     *
     * @param email of the user
     * @return user
     */
    Optional<User> findByEmail(final String email);
}
