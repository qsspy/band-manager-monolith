package com.qsspy.calendars.query.application.membersrestrictions.port.input;

import com.qsspy.calendars.command.domain.entry.EventKind;
import com.qsspy.commons.architecture.cqrs.QueryResult;
import lombok.Builder;
import org.springframework.lang.Nullable;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.UUID;

public record GetCalendarEntryMemberRestrictionQueryResult(
        Map<UUID, EntryRestriction> entryRestrictions
) implements QueryResult {

    public record EntryRestriction(
            EventKind eventKind,
            LocalDateTime eventDate,
            Map<UUID, Member> memberPrivileges
    ) {

        @Builder
        public record Member(
                String memberEmail,
                @Nullable
                Boolean canSeeCalendarEntry,
                @Nullable
                Boolean canSeeCalendarEntryPayment,
                @Nullable
                Boolean canSeeCalendarEntryDetails
        ) { }
    }
}
