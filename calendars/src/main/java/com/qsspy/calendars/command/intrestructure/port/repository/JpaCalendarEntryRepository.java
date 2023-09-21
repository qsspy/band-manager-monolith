package com.qsspy.calendars.command.intrestructure.port.repository;

import com.qsspy.jpadatamodel.entity.CalendarEntryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
interface JpaCalendarEntryRepository extends JpaRepository<CalendarEntryEntity, UUID> {
}
