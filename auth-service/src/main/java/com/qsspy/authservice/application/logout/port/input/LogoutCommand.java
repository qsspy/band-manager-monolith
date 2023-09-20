package com.qsspy.authservice.application.logout.port.input;

import com.qsspy.commons.architecture.cqrs.Command;
import com.qsspy.commons.architecture.cqrs.CommandValidationException;

public record LogoutCommand(String token) implements Command {

    @Override
    public void validate() {
        if(token == null || token.isBlank()) {
            throw new CommandValidationException("Command cannot blank");
        }
    }
}
