package com.qsspy.finances.command.infrastructure.port.repository;

import com.qsspy.domain.finances.FinanceEntry;
import com.qsspy.finances.command.application.addition.port.output.FinanceEntrySaveRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
class DatabaseFinanceEntryRepository implements FinanceEntrySaveRepository {

    private final JpaFinanceEntryRepository repository;

    @Override
    public void save(final FinanceEntry entry) {
        repository.save(entry);
    }
}
