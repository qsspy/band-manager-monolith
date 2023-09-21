package com.qsspy.calendars.command.intrestructure.port.repository;

import com.qsspy.calendars.command.application.entry.common.port.output.CalendarEntrySaveRepository;
import com.qsspy.calendars.command.application.entry.edit.port.output.CalendarEntryGetRepository;
import com.qsspy.calendars.command.domain.entry.CalendarEntry;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Component
@RequiredArgsConstructor
class DatabaseCalendarEntryRepository implements CalendarEntrySaveRepository, CalendarEntryGetRepository {

    private final JpaCalendarEntryRepository jpaRepository;

    @Override
    public void save(final CalendarEntry entry) {
        final var entity = DomainToPersistentEntityMapper.toEntity(entry);
        jpaRepository.save(entity);
    }


    @Override
    public Optional<CalendarEntry> findById(final UUID entryId) {
        return jpaRepository
                .findById(entryId)
                .map(PersistentEntityToDomainMapper::toEntity);
    }
}
