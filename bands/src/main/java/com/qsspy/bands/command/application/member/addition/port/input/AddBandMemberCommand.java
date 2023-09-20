package com.qsspy.bands.command.application.member.addition.port.input;

import com.qsspy.commons.architecture.cqrs.Command;
import com.qsspy.commons.architecture.cqrs.CommandValidationException;

import java.util.UUID;

public record AddBandMemberCommand(
        String userEmail,
        UUID bandId
) implements Command {
    @Override
    public void validate() {
        if(userEmail == null || userEmail.isBlank()) {
            throw new CommandValidationException("User email cannot be blank");
        }
        if(bandId == null) {
            throw new CommandValidationException("Band Id cannot be null");
        }
    }
}
