package com.qsspy.bands.command.infrastucture.adapter.controller;

import com.qsspy.bands.command.application.defaultprivileges.port.input.ChangeBandDefaultPrivilegesCommand;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.UUID;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
final class RequestToCommandMapper {

    static ChangeBandDefaultPrivilegesCommand toCommand(final UUID bandId, final ChangeBandDefaultPrivilegesRequestBody requestBody) {
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
}
