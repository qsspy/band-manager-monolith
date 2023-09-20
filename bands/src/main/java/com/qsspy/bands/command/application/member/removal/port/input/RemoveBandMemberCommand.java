package com.qsspy.bands.command.application.member.removal.port.input;

import com.qsspy.commons.architecture.cqrs.Command;
import com.qsspy.commons.architecture.cqrs.CommandValidationException;

import java.util.UUID;

public record RemoveBandMemberCommand(
        UUID bandId,
        UUID memberId
) implements Command {
    @Override
    public void validate() {
        if(bandId == null) {
            throw new CommandValidationException("Band id cannot be null!");
        }
        if(memberId == null) {
            throw new CommandValidationException("Member id cannot be null!");
        }
    }
}
