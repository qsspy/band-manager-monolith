package com.qsspy.authservice.application.register.port.input;

import com.qsspy.commons.architecture.cqrs.Command;
import com.qsspy.commons.architecture.cqrs.CommandValidationException;
import lombok.Builder;

@Builder
public record RegisterCommand(
        String email,
        String password,
        String firstName,
        String lastName
) implements Command {

    @Override
    public void validate() {
        if(email == null || email.isBlank()) {
            throw new CommandValidationException("Email cannot be blank");
        }
        if(password == null || password.isBlank()) {
            throw new CommandValidationException("Password cannot be blank");
        }
        if(firstName == null || firstName.isBlank()) {
            throw new CommandValidationException("First name cannot be blank");
        }
        if(lastName == null || lastName.isBlank()) {
            throw new CommandValidationException("Last name cannot be blank");
        }
    }
}
