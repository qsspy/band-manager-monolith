package com.qsspy.commons.architecture.port.output.publisher;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.UUID;

public record MeasurementNotificationEvent(
        UUID eventId,
        long occurredOn,

        @JsonIgnore
        MeasurementType measurementType,
        int recordNumber,
        int description
) implements NotificationEvent {

    private static final int EVENT_VERSION = 1;
    private static final String EVENT_TYPE = "measurement";

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
