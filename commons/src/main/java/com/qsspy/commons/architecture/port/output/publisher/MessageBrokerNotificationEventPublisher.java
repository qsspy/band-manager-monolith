package com.qsspy.commons.architecture.port.output.publisher;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Collection;
import java.util.EnumMap;
import java.util.Map;
import java.util.concurrent.*;

@Slf4j
@Component
@RequiredArgsConstructor
class MessageBrokerNotificationEventPublisher implements NotificationEventPublisher {

    private static final String EVENT_TYPE_HEADER_NAME = "type";
    private static final String EVENT_VERSION_HEADER_NAME = "version";
    private static final String PARTITION_KEY_HEADER_NAME = "partitionKey";

    private final StreamBridge streamBridge;

    private final ExecutorService asyncPublishingExecutor = Executors.newVirtualThreadPerTaskExecutor();

    private final Map<MeasurementType, Long> measurementFilter = new ConcurrentHashMap<>();
    private static final long CACHE_MILLIS_TIME = 1000;

    @Override
    public void publish(final NotificationEvent event) {

        if(event instanceof MeasurementNotificationEvent event1) {
            final var type = event1.measurementType();
            final var cachedTypeTime = measurementFilter.get(type);
            if(cachedTypeTime != null) {
                if(Instant.now().toEpochMilli() - CACHE_MILLIS_TIME < cachedTypeTime) {
                    log.info("Skip publishing");
                    return;
                }
                measurementFilter.put(type, Instant.now().toEpochMilli());
            }
            measurementFilter.put(type, Instant.now().toEpochMilli());
        }

        final var message = MessageBuilder
                .withPayload(event)
                .setHeader(EVENT_TYPE_HEADER_NAME, event.eventType())
                .setHeader(EVENT_VERSION_HEADER_NAME, event.eventVersion())
                .setHeader(PARTITION_KEY_HEADER_NAME, event.partitionKey())
                .build();

        log.info("Publishing event: {}", message);

        streamBridge.send(event.destinationBindingName(), message);
    }

    @Override
    public void publishAll(final Collection<NotificationEvent> events, final PublishingMode publishingMode) {
        switch (publishingMode) {
            case SYNC -> publishSynchronously(events);
            case ASYNC -> publishAsynchronously(events);
            case BATCH_ASYNC_WITH_BLOCKING -> publishBatchAsyncWithBlocking(events);
        }
    }

    private void publishBatchAsyncWithBlocking(final Collection<NotificationEvent> events) {
        final var publishingTasks = new ArrayList<Future<?>>();

        for(final var event : events) {
            publishingTasks.add(asyncPublishingExecutor.submit(() -> publish(event)));
        }

        publishingTasks.forEach(job -> {
            try {
                job.get();
            } catch (InterruptedException | ExecutionException e) {
                throw new RuntimeException(e);
            }
        });
    }

    private void publishAsynchronously(final Collection<NotificationEvent> events) {
        events.forEach(event -> asyncPublishingExecutor.submit(() -> publish(event)));
    }

    private void publishSynchronously(final Collection<NotificationEvent> events) {
        events.forEach(this::publish);
    }
}
