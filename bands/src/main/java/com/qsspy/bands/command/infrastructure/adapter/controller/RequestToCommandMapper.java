package com.qsspy.bands.command.infrastructure.adapter.controller;

import com.qsspy.bands.command.application.defaultprivileges.port.input.ChangeBandDefaultPrivilegesCommand;
import com.qsspy.bands.command.application.member.changeprivileges.port.input.ChangeMemberPrivilegesCommand;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.UUID;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
final class RequestToCommandMapper {

    static ChangeBandDefaultPrivilegesCommand toCommand(final UUID bandId, final ChangeBandPrivilegesRequestBody requestBody) {
        return ChangeBandDefaultPrivilegesCommand.builder()
                .bandId(bandId)
                .privilegesToChange(
                        ChangeBandDefaultPrivilegesCommand.Privileges.builder()
                                .canAccessCalendar(requestBody.canAccessCalendar())
                                .canAddCalendarEntries(requestBody.canAddCalendarEntries())
                                .canEditCalendarEntries(requestBody.canEditCalendarEntries())
                                .canDeleteCalendarEntries(requestBody.canDeleteCalendarEntries())
                                .canAccessFinanceHistory(requestBody.canAccessFinanceHistory())
                                .canAddFinanceEntries(requestBody.canAddFinanceEntries())
                                .canSeeFinanceIncomeEntries(requestBody.canSeeFinanceIncomeEntries())
                                .canSeeFinanceOutcomeEntries(requestBody.canSeeFinanceOutcomeEntries())
                                .canSeeCalendarEntryByDefault(requestBody.canSeeCalendarEntryByDefault())
                                .canSeeCalendarEntryPaymentByDefault(requestBody.canSeeCalendarEntryPaymentByDefault())
                                .canSeeCalendarEntryDetailsByDefault(requestBody.canSeeCalendarEntryDetailsByDefault())
                                .build()
                )
                .build();
    }

    static ChangeMemberPrivilegesCommand toCommand(final UUID bandId, final UUID memberId, final ChangeBandMemberPrivilegesRequestBody requestBody) {
        return ChangeMemberPrivilegesCommand.builder()
                .bandId(bandId)
                .memberId(memberId)
                .privilegesToChange(
                        ChangeMemberPrivilegesCommand.Privileges.builder()
                                .canAccessCalendar(requestBody.canAccessCalendar())
                                .canAddCalendarEntries(requestBody.canAddCalendarEntries())
                                .canEditCalendarEntries(requestBody.canEditCalendarEntries())
                                .canDeleteCalendarEntries(requestBody.canDeleteCalendarEntries())
                                .canAccessFinanceHistory(requestBody.canAccessFinanceHistory())
                                .canAddFinanceEntries(requestBody.canAddFinanceEntries())
                                .canSeeFinanceIncomeEntries(requestBody.canSeeFinanceIncomeEntries())
                                .canSeeFinanceOutcomeEntries(requestBody.canSeeFinanceOutcomeEntries())
                                .build()
                )
                .build();
    }
}
