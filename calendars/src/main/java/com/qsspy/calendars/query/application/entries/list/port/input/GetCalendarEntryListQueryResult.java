package com.qsspy.calendars.query.application.entries.list.port.input;

import com.qsspy.commons.architecture.cqrs.QueryResult;
import com.qsspy.domain.calendar.EventKind;
import lombok.Builder;
import org.springframework.lang.Nullable;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public record GetCalendarEntryListQueryResult(
        List<ListItem> entries
) implements QueryResult {

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
