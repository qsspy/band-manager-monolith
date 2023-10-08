package com.qsspy.commons.architecture.port.output.publisher;

import java.util.UUID;

public interface NotificationEvent {

    /**
     * Unique event identifier
     */
    UUID eventId();

    /**
     * Type of the event.
     * Used for event type identification if many types of events are published to the one channel
     */
    String eventType();

    /**
     * Version of event.
     */
    int eventVersion();

    /**
     * Timestamp of event occurrence
     */
    long occurredOn();

    /**
     * Name of binding of event publishing destination
     */
    String destinationBindingName();

    /**
     * Value of partition key
     */
    Object partitionKey();
}
