package com.qsspy.authservice.application.logout;

import com.qsspy.authservice.application.logout.port.input.LogoutCommand;
import com.qsspy.authservice.application.logout.port.input.LogoutCommandHandler;
import org.springframework.stereotype.Service;

@Service
class LogoutCommandHandlerImpl implements LogoutCommandHandler {

    @Override
    public void handle(final LogoutCommand command) {
        command.validate();

        //Do nothing
    }
}
