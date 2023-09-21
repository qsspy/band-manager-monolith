package com.qsspy.calendars.command.application.entry.restriction;

import com.qsspy.calendars.command.application.entry.restriction.port.input.RestrictMemberPrivilegesForEntryCommand;
import com.qsspy.calendars.command.domain.entry.dto.RestrictedMemberPrivilegesData;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
final class CommandToDtoMapper {

    static RestrictedMemberPrivilegesData toDomainData(final RestrictMemberPrivilegesForEntryCommand command) {
        return RestrictedMemberPrivilegesData.builder()
                .memberId(command.memberId())
                .canSeeCalendarEntry(command.canSeeCalendarEntry())
                .canSeeCalendarEntryPayment(command.canSeeCalendarEntryPayment())
                .canSeeCalendarEntryDetails(command.canSeeCalendarEntryDetails())
                .build();
    }
}
