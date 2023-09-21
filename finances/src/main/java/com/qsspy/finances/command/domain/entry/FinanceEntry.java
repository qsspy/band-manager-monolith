package com.qsspy.finances.command.domain.entry;

import com.qsspy.commons.architecture.ddd.DomainValidationException;
import com.qsspy.commons.architecture.ddd.AggregateRoot;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import org.springframework.lang.Nullable;

import java.math.BigDecimal;
import java.util.UUID;

@Builder(access = AccessLevel.PACKAGE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class FinanceEntry implements AggregateRoot {

    private final AggregateId id;
    private final BandId bandId;
    @Nullable
    private final Description description;
    private final Amount amount;
    private final Initiator initiator;
    private final boolean isOutcome;

    void validateCurrentState() {
        if(id == null) {
            throw new DomainValidationException("Aggregate id cannot be null!");
        }
        if(bandId == null) {
            throw new DomainValidationException("Band id cannot be null!");
        }
        if(initiator == null) {
            throw new DomainValidationException("Initiator cannot be null!");
        }
        if(amount == null) {
            throw new DomainValidationException("Amount cannot be null!");
        }

        id.validate();
        bandId.validate();
        amount.validate();
        initiator.validate();
        if(description != null) {
            description.validate();
        }
    }

    public Snapshot takeSnapshot() {
        return Snapshot.builder()
                .id(id.value())
                .bandId(bandId.value())
                .description(description != null ? description.text() : null)
                .amount(amount.value())
                .initiatorEmail(initiator.email())
                .isOutcome(isOutcome)
                .build();
    }

    @Builder
    public record Snapshot(
            UUID id,
            UUID bandId,
            BigDecimal amount,
            @Nullable
            String description,
            String initiatorEmail,
            boolean isOutcome
    ) { }
}
