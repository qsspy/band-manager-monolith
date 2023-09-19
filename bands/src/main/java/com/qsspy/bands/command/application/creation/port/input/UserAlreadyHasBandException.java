package com.qsspy.bands.command.application.creation.port.input;

public class UserAlreadyHasBandException extends RuntimeException {
    public UserAlreadyHasBandException() {
        super("User is member/admin of another band already!");
    }
}
