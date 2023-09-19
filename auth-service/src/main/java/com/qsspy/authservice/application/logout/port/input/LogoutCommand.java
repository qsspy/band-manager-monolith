package com.qsspy.authservice.application.logout.port.input;

import com.qsspy.authservice.application.common.Command;
import com.qsspy.authservice.application.common.CommandValidationException;

public record LogoutCommand(String token) implements Command {

    @Override
    public void validate() {
        if(token == null || token.isBlank()) {
            throw new CommandValidationException("Command cannot blank");
        }
    }
}
