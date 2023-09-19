package com.qsspy.bands.command.application.creation;

import com.qsspy.bands.command.application.creation.port.input.CreateBandCommand;
import com.qsspy.bands.command.application.creation.port.input.CreateBandCommandHandler;
import com.qsspy.bands.command.application.creation.port.input.UserAlreadyHasBandException;
import com.qsspy.bands.command.application.creation.port.output.BandSaveRepository;
import com.qsspy.bands.command.domain.band.BandFactory;
import com.qsspy.bands.command.domain.band.dto.BandCreationData;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
class CreateBandCommandHandlerImpl implements CreateBandCommandHandler {

    private final BandSaveRepository saveRepository;

    @Override
    public void handle(final CreateBandCommand command) {
        command.validate();

        if(command.userMemberBandId() != null || command.userOwnBandId() != null) {
            throw new UserAlreadyHasBandException();
        }

        final var bandCreationData = new BandCreationData(command.creatorId(), command.bandName());
        final var band = BandFactory.createrNewBand(bandCreationData);

        saveRepository.save(band);
    }
}
