package com.qsspy.authservice.application.logout.port.input;

import com.qsspy.commons.architecture.Command;
import com.qsspy.commons.architecture.CommandValidationException;

public record LogoutCommand(String token) implements Command {

    @Override
    public void validate() {
        if(token == null || token.isBlank()) {
            throw new CommandValidationException("Command cannot blank");
        }
    }
}
