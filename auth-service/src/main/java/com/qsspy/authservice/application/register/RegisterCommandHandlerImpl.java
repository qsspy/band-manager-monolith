package com.qsspy.authservice.application.register;

import com.qsspy.authservice.application.register.port.input.RegisterCommand;
import com.qsspy.authservice.application.register.port.input.RegisterCommandHandler;
import com.qsspy.authservice.application.register.port.input.UserAlreadyExistsException;
import com.qsspy.authservice.application.register.port.output.UserRegisterRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
class RegisterCommandHandlerImpl implements RegisterCommandHandler {

    private final UserRegisterRepository registerRepository;

    @Override
    public void handle(final RegisterCommand command) {
        command.validate();

        if(registerRepository.existsByEmail(command.email())) {
            throw new UserAlreadyExistsException();
        }
        registerRepository.save(command);
    }
}
