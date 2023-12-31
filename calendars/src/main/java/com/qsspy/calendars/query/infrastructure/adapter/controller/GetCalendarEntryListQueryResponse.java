package com.qsspy.calendars.query.infrastructure.adapter.controller;

import com.qsspy.domain.calendar.EventKind;
import lombok.Builder;
import org.springframework.lang.Nullable;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

record GetCalendarEntryListQueryResponse(List<ListItem> entries) {

        @Builder
        public record ListItem(
                UUID id,
                EventKind eventKind,
                LocalDateTime eventDate,
                @Nullable
                BigDecimal amount,
                boolean canSeeDetails
        ) {}
}
