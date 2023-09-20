package com.qsspy.finances.command.domain.entry;

import com.qsspy.finances.command.domain.entry.dto.FinanceEntrySpecification;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
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

    public static FinanceEntry instantiateFromSnapshot(final FinanceEntry.Snapshot snapshot) {
        final var entry = FinanceEntry.builder()
                .id(new AggregateId(snapshot.id()))
                .bandId(new BandId(snapshot.bandId()))
                .description(snapshot.description() != null ? new Description(snapshot.description()) : null)
                .initiator(new Initiator(snapshot.initiatorEmail()))
                .isOutcome(snapshot.isOutcome())
                .build();

        entry.validateCurrentState();

        return entry;
    }
}
