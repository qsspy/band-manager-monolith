package com.qsspy.bands.command.domain.band;

import com.qsspy.bands.command.domain.band.dto.BandCreationData;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.UUID;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class BandFactory {

    public static Band createrNewBand(final BandCreationData creationData) {
        final var band = Band.builder()
                .id(new AggregateId(UUID.randomUUID()))
                .name(new BandName(creationData.bandName()))
                .adminId(new AdminId(creationData.creatorId()))
                .build();

        band.validateCurrentState();

        return band;
    }

    public static Band instantiateFromSnapshot(final Band.Snapshot snapshot) {
        return Band.builder()
                .id(new AggregateId(snapshot.id()))
                .name(new BandName(snapshot.name()))
                .adminId(new AdminId(snapshot.adminId()))
                .build();
    }
}
