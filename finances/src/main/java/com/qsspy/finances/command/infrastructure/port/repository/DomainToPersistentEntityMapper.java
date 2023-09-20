package com.qsspy.finances.command.infrastructure.port.repository;

import com.qsspy.finances.command.domain.entry.FinanceEntry;
import com.qsspy.jpadatamodel.entity.FinanceEntryEntity;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
final class DomainToPersistentEntityMapper {

    static FinanceEntryEntity toEntity(final FinanceEntry financeEntry) {

        final var snapshot = financeEntry.takeSnapshot();

        return FinanceEntryEntity.builder()
                .id(snapshot.id())
                .bandId(snapshot.bandId())
                .amount(snapshot.amount())
                .description(snapshot.description())
                .initiatorEmail(snapshot.initiatorEmail())
                .isOutcome(snapshot.isOutcome())
                .build();
    }
}
