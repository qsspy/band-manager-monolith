package com.qsspy.bands.command.infrastucture.port.repository;

import com.qsspy.domain.band.Band;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
interface JpaBandRepository extends JpaRepository<Band, UUID> {

    Optional<Band> findByIdValue(final UUID uuid);
}
