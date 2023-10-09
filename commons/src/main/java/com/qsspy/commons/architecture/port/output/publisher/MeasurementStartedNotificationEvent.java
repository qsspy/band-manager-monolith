package com.qsspy.commons.architecture.port.output.publisher;

import java.util.UUID;

public record MeasurementStartedNotificationEvent(
        UUID eventId,
        long occurredOn,

        MeasurementType measurementType
) implements NotificationEvent {

    private static final int EVENT_VERSION = 1;
    private static final String EVENT_TYPE = "measurement.started";

    @Override
    public String eventType() {
        return EVENT_TYPE;
    }

    @Override
    public int eventVersion() {
        return EVENT_VERSION;
    }

    @Override
    public String destinationBindingName() {
        return "measurements";
    }

    @Override
    public Object partitionKey() {
        return eventId;
    }
}
