package com.qsspy.bands.command.application.defaultprivileges;

import com.qsspy.bands.command.application.common.port.output.BandSaveRepository;
import com.qsspy.bands.command.application.common.port.output.GetBandByIdRepository;
import com.qsspy.bands.command.application.defaultprivileges.port.input.ChangeBandDefaultPrivilegesCommand;
import com.qsspy.bands.command.application.defaultprivileges.port.input.ChangeBandDefaultPrivilegesCommandHandler;
import com.qsspy.commons.architecture.port.output.publisher.MeasurementNotificationEvent;
import com.qsspy.commons.architecture.port.output.publisher.NotificationEventPublisher;
import com.qsspy.domain.band.Band;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.UUID;

import static com.qsspy.commons.architecture.port.output.publisher.MeasurementType.DEFAULT_PRIVILEGES_CHANGE;

@Service
@RequiredArgsConstructor
@Transactional
class ChangeBandDefaultPrivilegesCommandHandlerImpl implements ChangeBandDefaultPrivilegesCommandHandler {

    private final GetBandByIdRepository getRepository;
    private final BandSaveRepository saveRepository;

    @Override
    public void handle(final ChangeBandDefaultPrivilegesCommand command) {
        command.validate();

        getRepository
                .findById(command.bandId())
                .ifPresentOrElse(
                        band -> changeBandDefaultPrivilegesAndSave(band, command),
                        () -> {
                            throw new IllegalStateException("Could not find band to change default privileges");
                        }
                );
    }

    private void changeBandDefaultPrivilegesAndSave(final Band band, final ChangeBandDefaultPrivilegesCommand command) {
        final var privilegeSpecification = CommandToDomainDtoMapper.toSpecification(command);
        band.changeDefaultPrivileges(privilegeSpecification);
        saveRepository.save(band);
    }
}
