package com.qsspy.bands.command.infrastructure.port.repository;

import com.qsspy.bands.command.application.common.port.output.BandSaveRepository;
import com.qsspy.bands.command.application.common.port.output.GetBandByIdRepository;
import com.qsspy.domain.band.Band;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Component
@RequiredArgsConstructor
class DatabaseBandRepository implements BandSaveRepository, GetBandByIdRepository {

    private final JpaBandRepository bandRepository;

    @Override
    public void save(final Band band) {
        bandRepository.save(band);
    }

    @Override
    public Optional<Band> findById(final UUID bandId) {
        return bandRepository.findByIdValue(bandId);
    }
}
