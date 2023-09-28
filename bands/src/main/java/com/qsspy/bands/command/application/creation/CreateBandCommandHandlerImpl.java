package com.qsspy.bands.command.application.creation;

import com.qsspy.bands.command.application.common.port.output.BandSaveRepository;
import com.qsspy.bands.command.application.common.port.output.BandUserGetRepository;
import com.qsspy.bands.command.application.creation.port.input.CreateBandCommand;
import com.qsspy.bands.command.application.creation.port.input.CreateBandCommandHandler;
import com.qsspy.domain.band.BandFactory;
import com.qsspy.domain.band.dto.BandCreationData;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
class CreateBandCommandHandlerImpl implements CreateBandCommandHandler {

    private final BandSaveRepository saveRepository;
    private final BandUserGetRepository userGetRepository;

    @Override
    public void handle(final CreateBandCommand command) {
        command.validate();

        final var bandCreationData = new BandCreationData(command.creatorId(), command.bandName());

        userGetRepository.findById(command.creatorId())
                .ifPresentOrElse(
                        user -> {
                            final var band = BandFactory.createrNewBand(bandCreationData, user);
                            saveRepository.save(band);
                        },
                        () -> { throw new IllegalStateException("Could not find band creator user.");}
                );
    }
}
