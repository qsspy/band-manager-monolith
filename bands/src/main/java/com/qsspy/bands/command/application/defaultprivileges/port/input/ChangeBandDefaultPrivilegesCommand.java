package com.qsspy.bands.command.application.defaultprivileges.port.input;

import com.qsspy.commons.architecture.cqrs.Command;
import com.qsspy.commons.architecture.cqrs.CommandValidationException;
import lombok.Builder;
import org.springframework.lang.Nullable;

import java.util.UUID;

@Builder
public record ChangeBandDefaultPrivilegesCommand(
        UUID bandId,
        Privileges privilegesToChange
) implements Command {
    @Override
    public void validate() {
        if(bandId == null) {
            throw new CommandValidationException("Band id cannot be null!");
        }
        if(privilegesToChange == null) {
            throw new CommandValidationException("Privileges cannot be null!");
        }
    }

    @Builder
    public record Privileges(
            @Nullable
            Boolean canAccessCalendar,
            @Nullable
            Boolean canAddCalendarEntries,
            @Nullable
            Boolean canEditCalendarEntries,
            @Nullable
            Boolean canDeleteCalendarEntries,
            @Nullable
            Boolean canAccessFinanceHistory,
            @Nullable
            Boolean canAddFinanceEntries,
            @Nullable
            Boolean canSeeFinanceIncomeEntries,
            @Nullable
            Boolean canSeeFinanceOutcomeEntries,
            @Nullable
            Boolean canSeeCalendarEntryByDefault,
            @Nullable
            Boolean canSeeCalendarEntryPaymentByDefault,
            @Nullable
            Boolean canSeeCalendarEntryDetailsByDefault
    ) {}
}
