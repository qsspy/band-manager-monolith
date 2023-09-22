package com.qsspy.calendars.query.application.entries.details.port.input;

import com.qsspy.commons.architecture.cqrs.QueryResult;
import lombok.Builder;
import org.springframework.lang.Nullable;

import java.time.Duration;

@Builder
public record GetCalendarEntryDetailsQueryResult(
        @Nullable
        String address,
        @Nullable
        Duration eventDuration,
        @Nullable
        String description
) implements QueryResult { }
