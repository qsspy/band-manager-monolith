package com.qsspy.finances.command.infrastructure.port.repository;

import com.qsspy.finances.command.application.addition.port.output.FinanceEntrySaveRepository;
import com.qsspy.finances.command.domain.entry.FinanceEntry;
import com.qsspy.jpadatamodel.entity.FinanceEntryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
interface JpaFinanceEntryRepository extends JpaRepository<FinanceEntryEntity, UUID> {
}
