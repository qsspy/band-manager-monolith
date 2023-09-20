package com.qsspy.finances.command.application.addition;

import com.qsspy.finances.command.application.addition.port.input.AddFinanceEntryCommand;
import com.qsspy.finances.command.domain.entry.dto.FinanceEntrySpecification;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
final class CommandToDtoMapper {

    static FinanceEntrySpecification toSpecification(final AddFinanceEntryCommand command) {
        return FinanceEntrySpecification.builder()
                .bandId(command.bandId())
                .amount(command.amount())
                .description(command.description())
                .initiatorEmail(command.initiatorEmail())
                .isOutcome(command.isOutcome())
                .build();
    }
}
