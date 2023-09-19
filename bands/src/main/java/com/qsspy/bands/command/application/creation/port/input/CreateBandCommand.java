package com.qsspy.bands.command.application.creation.port.input;

import com.qsspy.commons.architecture.Command;
import com.qsspy.commons.architecture.CommandValidationException;
import lombok.Builder;
import org.springframework.lang.Nullable;

import java.util.UUID;

@Builder
public record CreateBandCommand(
        UUID creatorId,
        @Nullable
        UUID userMemberBandId,
        @Nullable
        UUID userOwnBandId,
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
