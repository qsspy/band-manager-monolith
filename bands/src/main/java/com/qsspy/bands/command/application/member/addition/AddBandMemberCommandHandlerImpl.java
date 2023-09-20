package com.qsspy.bands.command.application.member.addition;

import com.qsspy.bands.command.application.common.port.output.BandSaveRepository;
import com.qsspy.bands.command.application.common.port.output.GetBandByIdRepository;
import com.qsspy.bands.command.application.member.addition.port.input.AddBandMemberCommand;
import com.qsspy.bands.command.application.member.addition.port.input.AddBandMemberCommandHandler;
import com.qsspy.bands.command.application.member.addition.port.output.BandUserRepository;
import com.qsspy.bands.command.application.member.addition.port.output.dto.UserMembership;
import com.qsspy.bands.command.domain.band.Band;
import com.qsspy.commons.architecture.cqrs.CommandValidationException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Transactional
@Service
@RequiredArgsConstructor
class AddBandMemberCommandHandlerImpl implements AddBandMemberCommandHandler {

    private final GetBandByIdRepository getRepository;
    private final BandSaveRepository saveRepository;
    private final BandUserRepository bandMembershipRepository;

    @Override
    public void handle(final AddBandMemberCommand command) {
        command.validate();

        bandMembershipRepository.getUserMembershipInformation(command.userEmail()).ifPresentOrElse(
                membership -> {

                    if(membership.memberBandId() != null || membership.ownedBandId() != null) {
                        throw new CommandValidationException("User is member of another band already!");
                    }

                    getRepository
                            .findById(command.bandId())
                            .ifPresentOrElse(
                                    band -> this.updateBandMemberAndSave(band, membership),
                                    () -> { throw new IllegalStateException("Could not find band with given id"); }
                            );

                },
                () -> { throw new IllegalStateException("Could not find user with provided email"); }
        );
    }

    private void updateBandMemberAndSave(final Band band, final UserMembership membership) {
        band.addMember(membership.userId());
        saveRepository.save(band);
    }
}
