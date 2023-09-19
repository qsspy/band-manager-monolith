package com.qsspy.authservice.application.authorizer.port.output;

import com.qsspy.authservice.application.authorizer.port.input.UserContext;

import java.util.Optional;
import java.util.UUID;

public interface UserContextRepository {

    /**
     * Retrieves user context bu it's id
     *
     * @param userId user id
     * @return context of the user
     */
    Optional<UserContext> findById(final UUID userId);
}
