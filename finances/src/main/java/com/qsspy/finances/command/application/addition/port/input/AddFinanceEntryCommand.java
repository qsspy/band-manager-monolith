package com.qsspy.finances.command.application.addition.port.input;

import com.qsspy.commons.architecture.cqrs.Command;
import com.qsspy.commons.architecture.cqrs.CommandValidationException;
import lombok.Builder;
import org.springframework.lang.Nullable;

import java.math.BigDecimal;
import java.util.UUID;

@Builder
public record AddFinanceEntryCommand(
        UUID initiatorsBandId,
        UUID bandId,
        BigDecimal amount,
        @Nullable
        String description,
        String initiatorEmail,
        boolean isOutcome

) implements Command {
    @Override
    public void validate() {
        if(bandId == null) {
            throw new CommandValidationException("Band id cannot be null");
        }
        if(amount == null) {
            throw new CommandValidationException("Amount cannot be null");
        }
        if(amount.compareTo(BigDecimal.ZERO) == 0) {
            throw new CommandValidationException("Amount cannot be zero");
        }
        if(amount.compareTo(BigDecimal.ZERO) < 0 && !isOutcome) {
            throw new CommandValidationException("Income must be positive");
        }
        if(amount.compareTo(BigDecimal.ZERO) > 0 && isOutcome) {
            throw new CommandValidationException("Outcome must be positive");
        }
        if(initiatorEmail == null) {
            throw new CommandValidationException("Initiator email cannot be null");
        }
    }
}
