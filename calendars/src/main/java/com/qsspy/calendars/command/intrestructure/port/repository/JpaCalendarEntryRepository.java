package com.qsspy.calendars.command.intrestructure.port.repository;

import com.qsspy.domain.calendar.CalendarEntry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
interface JpaCalendarEntryRepository extends JpaRepository<CalendarEntry, UUID> {

    void deleteByBandIdValueAndIdValue(final UUID bandId, final UUID entryId);

    Optional<CalendarEntry> findByBandIdValueAndIdValue(final UUID bandId, final UUID entryId);
}
