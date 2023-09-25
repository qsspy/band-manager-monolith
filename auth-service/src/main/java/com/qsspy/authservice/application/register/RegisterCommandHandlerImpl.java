package com.qsspy.authservice.application.register;

import com.qsspy.authservice.application.register.port.input.RegisterCommand;
import com.qsspy.authservice.application.register.port.input.RegisterCommandHandler;
import com.qsspy.authservice.application.register.port.input.UserAlreadyExistsException;
import com.qsspy.authservice.application.register.port.output.UserRegisterRepository;
import jakarta.annotation.PostConstruct;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional
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

    //TODO remove in future
    @PostConstruct
    void initRegistration() {
        handle(new RegisterCommand("1@1.1", "12345", "1", "1"));
        handle(new RegisterCommand("2@2.2", "12345", "2", "2"));
        handle(new RegisterCommand("3@3.3", "12345", "3", "3"));
    }
}
