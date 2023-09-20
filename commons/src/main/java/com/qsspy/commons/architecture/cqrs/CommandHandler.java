package com.qsspy.commons.architecture.cqrs;

public interface CommandHandler<T extends Command> {

    /**
     * Handles the command
     *
     * @param command command to be handled
     */
     void handle(final T command);
}
