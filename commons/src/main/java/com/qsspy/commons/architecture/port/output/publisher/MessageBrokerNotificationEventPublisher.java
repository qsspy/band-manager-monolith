package com.qsspy.commons.architecture.port.output.publisher;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

@Slf4j
@Component
@RequiredArgsConstructor
class MessageBrokerNotificationEventPublisher implements NotificationEventPublisher {

    private static final String EVENT_TYPE_HEADER_NAME = "type";
    private static final String EVENT_VERSION_HEADER_NAME = "version";
    private static final String PARTITION_KEY_HEADER_NAME = "partitionKey";

    private final StreamBridge streamBridge;

    private final ExecutorService asyncPublishingExecutor = Executors.newVirtualThreadPerTaskExecutor();

    @Override
    public void publish(final NotificationEvent event) {

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
