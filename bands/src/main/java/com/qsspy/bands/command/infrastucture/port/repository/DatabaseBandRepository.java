package com.qsspy.bands.command.infrastucture.port.repository;

import com.qsspy.bands.command.application.common.port.output.BandSaveRepository;
import com.qsspy.bands.command.application.common.port.output.GetBandByIdRepository;
import com.qsspy.bands.command.domain.band.Band;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Component
@RequiredArgsConstructor
class DatabaseBandRepository implements BandSaveRepository, GetBandByIdRepository {

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
                            bandRepository.save(persistentBand);
                        },
                        () -> {
                            throw new IllegalStateException("Could not find admin of created band");
                        }
                );
    }

    @Override
    public Optional<Band> findById(final UUID bandId) {
        return bandRepository
                .findById(bandId)
                .map(PersistentEntityToDomainMapper::fromEntity);
    }
}
