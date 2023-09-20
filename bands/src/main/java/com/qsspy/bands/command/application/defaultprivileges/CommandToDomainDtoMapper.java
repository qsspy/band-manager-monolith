package com.qsspy.bands.command.application.defaultprivileges;

import com.qsspy.bands.command.application.defaultprivileges.port.input.ChangeBandDefaultPrivilegesCommand;
import com.qsspy.bands.command.domain.band.dto.DefaultPrivilegesChangeSpecification;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
final class CommandToDomainDtoMapper {

    static DefaultPrivilegesChangeSpecification toSpecification(final ChangeBandDefaultPrivilegesCommand command) {
        return DefaultPrivilegesChangeSpecification.builder()
                .canAccessCalendar(command.privilegesToChange().canAccessCalendar())
                .canAddCalendarEntries(command.privilegesToChange().canAddCalendarEntries())
                .canEditCalendarEntries(command.privilegesToChange().canEditCalendarEntries())
                .canDeleteCalendarEntries(command.privilegesToChange().canDeleteCalendarEntries())
                .canAccessFinanceHistory(command.privilegesToChange().canAccessFinanceHistory())
                .canAddFinanceEntries(command.privilegesToChange().canAddFinanceEntries())
                .canSeeFinanceIncomeEntries(command.privilegesToChange().canSeeFinanceIncomeEntries())
                .canSeeFinanceOutcomeEntries(command.privilegesToChange().canSeeFinanceOutcomeEntries())
                .canSeeCalendarEntryByDefault(command.privilegesToChange().canSeeCalendarEntryByDefault())
                .canSeeCalendarEntryPaymentByDefault(command.privilegesToChange().canSeeCalendarEntryPaymentByDefault())
                .canSeeCalendarEntryDetailsByDefault(command.privilegesToChange().canSeeCalendarEntryDetailsByDefault())
                .build();
    }
}
