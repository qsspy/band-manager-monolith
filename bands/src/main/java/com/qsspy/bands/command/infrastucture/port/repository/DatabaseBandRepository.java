package com.qsspy.bands.command.infrastucture.port.repository;

import com.qsspy.bands.command.application.creation.port.output.BandSaveRepository;
import com.qsspy.bands.command.domain.band.Band;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
class DatabaseBandRepository implements BandSaveRepository {

    private final JpaBandRepository bandRepository;
    private final JpaBandUserRepository userRepository;

    @Override
    public void save(final Band band) {

        final var bandSnapshot = band.takeSnapshot();

        userRepository
                .findById(bandSnapshot.adminId())
                .ifPresentOrElse(
                        bandAdmin -> {
                            final var persistentBand = DomainToPersistentEntityMapper.toEntity(bandSnapshot, bandAdmin);
                            bandAdmin.setOwnedBand(persistentBand);
                            bandRepository.save(persistentBand);
                        },
                        () -> {
                            throw new IllegalStateException("Could not find admin of created band");
                        }
                );
    }
}
