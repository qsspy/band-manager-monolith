package com.qsspy.calendars.query.infrastructure.adapter.controller;

import com.qsspy.commons.architecture.cqrs.QueryResult;
import lombok.Builder;
import org.springframework.lang.Nullable;

import java.time.Duration;

@Builder
record GetCalendarEntryDetailsQueryResponse(
        @Nullable
        String address,
        @Nullable
        Integer eventDurationHours,
        @Nullable
        String description
) implements QueryResult { }
