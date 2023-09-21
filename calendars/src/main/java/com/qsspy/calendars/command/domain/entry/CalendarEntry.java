package com.qsspy.calendars.command.domain.entry;

import com.qsspy.commons.architecture.cqrs.DomainValidationException;
import com.qsspy.commons.architecture.ddd.AggregateRoot;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import org.springframework.lang.Nullable;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.UUID;

@Builder(access = AccessLevel.PACKAGE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class CalendarEntry implements AggregateRoot {

    private final AggregateId id;
    private final BandId bandId;

    private final EventDate eventDate;
    private final EventKind eventKind;
    private final Amount amount;

    @Nullable
    private final Address address;
    @Nullable
    private final EventDuration eventDuration;
    @Nullable
    private final Description description;

    public Snapshot takeSnapshot() {
        return Snapshot.builder()
                .id(id.value())
                .bandId(bandId.value())
                .eventDate(eventDate.value())
                .eventKind(eventKind)
                .amount(amount.value())
                .address(address != null ? address.fullAddress() : null)
                .eventDuration(eventDuration != null ? eventDuration.value() : null)
                .description(description != null ? description.text() : null)
                .build();
    }

    @Builder
    public record Snapshot(
            UUID id,
            UUID bandId,
            LocalDateTime eventDate,
            EventKind eventKind,
            BigDecimal amount,
            @Nullable
            String address,
            @Nullable
            Duration eventDuration,
            @Nullable
            String description
    ) { }

    void validateCurrentState() {
        if(id == null) {
            throw new DomainValidationException("Aggregate id cannot be null!");
        }
        if(bandId == null) {
            throw new DomainValidationException("Band id cannot be null!");
        }
        if(eventDate == null) {
            throw new DomainValidationException("Event date cannot be null!");
        }
        if(eventKind == null) {
            throw new DomainValidationException("Event kind cannot be null!");
        }
        if(amount == null) {
            throw new DomainValidationException("Amount cannot be null!");
        }

        id.validate();
        bandId.validate();
        eventDate.validate();
        amount.validate();
        if(address != null) {
            address.validate();
        }
        if(eventDuration != null) {
            eventDuration.validate();
        }
        if(description != null) {
            description.validate();
        }
    }
}
