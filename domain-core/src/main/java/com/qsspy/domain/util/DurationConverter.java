package com.qsspy.domain.util;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.time.Duration;
import java.time.temporal.ChronoUnit;

@Converter(autoApply = true)
class DurationConverter implements AttributeConverter<Duration, Long> {
    @Override
    public Long convertToDatabaseColumn(final Duration attribute) {
        return attribute.toMillis();
    }

    @Override
    public Duration convertToEntityAttribute(final Long duration) {
        return Duration.of(duration, ChronoUnit.MILLIS);
    }
}
