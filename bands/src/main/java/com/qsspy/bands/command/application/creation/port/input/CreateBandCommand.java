package com.qsspy.bands.command.application.creation.port.input;

import com.qsspy.commons.architecture.cqrs.Command;
import com.qsspy.commons.architecture.cqrs.CommandValidationException;
import lombok.Builder;
import org.springframework.lang.Nullable;

import java.util.UUID;

@Builder
public record CreateBandCommand(
        UUID creatorId,
        String bandName
) implements Command {
    @Override
    public void validate() {
        if(creatorId == null) {
            throw new CommandValidationException("Creator id cannot be null!");
        }
        if(bandName == null || bandName.isBlank()) {
            throw new CommandValidationException("Band name cannot be blank!");
        }
    }
}
