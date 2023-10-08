package com.qsspy.commons.architecture.port.output.publisher;

import java.util.Collection;

public interface NotificationEventPublisher {

    /**
     * Publishes event to the output channel
     *
     * @param event event to be published
     */
    void publish(final NotificationEvent event);

    /**
     * Publishes events to the output channel
     *
     * @param events events to be published
     * @param publishingMode how the events should be published
     */
    void publishAll(final Collection<NotificationEvent> events, final PublishingMode publishingMode);

    default void publishAll(final Collection<NotificationEvent> events) {
        publishAll(events, PublishingMode.SYNC);
    }
}
