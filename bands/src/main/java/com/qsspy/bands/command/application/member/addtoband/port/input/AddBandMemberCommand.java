package com.qsspy.bands.command.application.member.addtoband.port.input;

import com.qsspy.commons.architecture.cqrs.Command;
import com.qsspy.commons.architecture.cqrs.CommandValidationException;

import java.util.UUID;

public record AddBandMemberCommand(
        UUID userId,
        UUID bandId
) implements Command {
    @Override
    public void validate() {
        if(userId == null) {
            throw new CommandValidationException("User Id cannot be null");
        }
        if(bandId == null) {
            throw new CommandValidationException("Band Id cannot be null");
        }
    }
}
