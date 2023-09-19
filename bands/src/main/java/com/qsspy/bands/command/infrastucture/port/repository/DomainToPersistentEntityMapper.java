package com.qsspy.bands.command.infrastucture.port.repository;

import com.qsspy.bands.command.domain.band.Band;
import com.qsspy.jpadatamodel.entity.BandEntity;
import com.qsspy.jpadatamodel.entity.UserEntity;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
class DomainToPersistentEntityMapper {

    static BandEntity toEntity(final Band.Snapshot bandSnapshot, final UserEntity userEntity) {
        return BandEntity.builder()
                .id(bandSnapshot.id())
                .name(bandSnapshot.name())
                .bandAdmin(userEntity)
                .build();
    }
}
