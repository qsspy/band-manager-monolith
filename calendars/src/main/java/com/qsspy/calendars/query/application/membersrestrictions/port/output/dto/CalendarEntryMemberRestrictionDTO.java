package com.qsspy.calendars.query.application.membersrestrictions.port.output.dto;

import org.springframework.lang.Nullable;

import java.time.LocalDateTime;
import java.util.UUID;

public record CalendarEntryMemberRestrictionDTO(
        UUID entryId,
        String eventKind,
        LocalDateTime eventDate,

        UUID memberId,
        String memberEmail,

        @Nullable
        Boolean canSeeCalendarEntry,
        @Nullable
        Boolean canSeeCalendarEntryPayment,
        @Nullable
        Boolean canSeeCalendarEntryDetails
) { }
