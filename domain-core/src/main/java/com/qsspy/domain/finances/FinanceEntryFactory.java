package com.qsspy.domain.finances;

import com.qsspy.domain.finances.dto.FinanceEntrySpecification;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.UUID;


@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class FinanceEntryFactory {

    public static FinanceEntry createNewFinanceEntry(final FinanceEntrySpecification spec) {
        final var entry = FinanceEntry.builder()
                .id(new AggregateId(UUID.randomUUID()))
                .bandId(new BandId(spec.bandId()))
                .description(spec.description() != null ? new Description(spec.description()) : null)
                .amount(new Amount(spec.amount()))
                .initiator(new Initiator(spec.initiatorEmail()))
                .isOutcome(spec.isOutcome())
                .build();

        entry.validateCurrentState();

        return entry;
    }
}
