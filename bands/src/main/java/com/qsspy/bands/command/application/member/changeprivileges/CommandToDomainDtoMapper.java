package com.qsspy.bands.command.application.member.changeprivileges;

import com.qsspy.bands.command.application.member.changeprivileges.port.input.ChangeMemberPrivilegesCommand;
import com.qsspy.domain.band.dto.MemberPrivilegesChangeSpecification;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
final class CommandToDomainDtoMapper {

    static MemberPrivilegesChangeSpecification toSpecification(final ChangeMemberPrivilegesCommand.Privileges privileges) {
        return MemberPrivilegesChangeSpecification.builder()
                .canAccessCalendar(privileges.canAccessCalendar())
                .canAddCalendarEntries(privileges.canAddCalendarEntries())
                .canEditCalendarEntries(privileges.canEditCalendarEntries())
                .canDeleteCalendarEntries(privileges.canDeleteCalendarEntries())
                .canAccessFinanceHistory(privileges.canAccessFinanceHistory())
                .canAddFinanceEntries(privileges.canAddFinanceEntries())
                .canSeeFinanceIncomeEntries(privileges.canSeeFinanceIncomeEntries())
                .canSeeFinanceOutcomeEntries(privileges.canSeeFinanceOutcomeEntries())
                .build();
    }
}
