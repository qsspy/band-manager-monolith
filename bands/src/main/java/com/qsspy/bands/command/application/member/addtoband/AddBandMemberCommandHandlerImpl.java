package com.qsspy.bands.command.application.member.addtoband;

import com.qsspy.bands.command.application.common.port.output.BandSaveRepository;
import com.qsspy.bands.command.application.common.port.output.GetBandByIdRepository;
import com.qsspy.bands.command.application.member.addtoband.port.input.AddBandMemberCommand;
import com.qsspy.bands.command.application.member.addtoband.port.input.AddBandMemberCommandHandler;
import com.qsspy.bands.command.domain.band.Band;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Transactional
@Service
@RequiredArgsConstructor
class AddBandMemberCommandHandlerImpl implements AddBandMemberCommandHandler {

    private final GetBandByIdRepository getRepository;
    private final BandSaveRepository saveRepository;

    @Override
    public void handle(final AddBandMemberCommand command) {
        command.validate();

        getRepository
                .findById(command.bandId())
                .ifPresentOrElse(
                        band -> this.updateBandMemberAndSave(band, command),
                        () -> { throw new IllegalStateException("Could not find band with given id"); }
                );
    }

    private void updateBandMemberAndSave(final Band band, final AddBandMemberCommand command) {
        band.addMember(command.userId());
        saveRepository.save(band);
    }
}
