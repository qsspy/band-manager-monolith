package com.qsspy.finances.command.infrastructure.port.repository;

import com.qsspy.finances.command.application.addition.port.output.FinanceEntrySaveRepository;
import com.qsspy.finances.command.domain.entry.FinanceEntry;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
class DatabaseFinanceEntryRepository implements FinanceEntrySaveRepository {

    private final JpaFinanceEntryRepository repository;

    @Override
    public void save(final FinanceEntry entry) {
        final var persistentEntity = DomainToPersistentEntityMapper.toEntity(entry);
        repository.save(persistentEntity);
    }
}
