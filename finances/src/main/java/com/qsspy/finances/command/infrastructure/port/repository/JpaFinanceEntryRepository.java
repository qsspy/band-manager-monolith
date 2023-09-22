package com.qsspy.finances.command.infrastructure.port.repository;

import com.qsspy.domain.finances.FinanceEntry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
interface JpaFinanceEntryRepository extends JpaRepository<FinanceEntry, UUID> {
}
