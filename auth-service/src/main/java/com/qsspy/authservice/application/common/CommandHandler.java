package com.qsspy.authservice.application.common;

public interface CommandHandler<T extends Command> {

    /**
     * Handles the command
     *
     * @param command command to be handled
     */
     void handle(final T command);
}
