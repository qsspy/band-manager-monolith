package com.qsspy.domain.band;

import com.qsspy.domain.band.dto.BandCreationData;
import com.qsspy.domain.user.User;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.Collections;
import java.util.UUID;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Slf4j
public final class BandFactory {

    public static Band createrNewBand(final BandCreationData creationData, final User adminUser) {
        //TODO change to random generation
        final var id = UUID.fromString("9f77ac07-9422-4390-bf4d-4b039f7416ba");
        final var band = Band.builder()
                .id(new AggregateId(id))
                .name(new BandName(creationData.bandName()))
                .adminUser(adminUser)
                .defaultBandPrivileges(DefaultBandPrivileges.newBaseDefaultPrivileges(new EntityId(id)))
                .memberPrivileges(Collections.emptyList())
                .bandMembers(Collections.emptyList())
                .build();

        band.validateCurrentState();

        log.info("Created band with id : {}", id);

        return band;
    }
}