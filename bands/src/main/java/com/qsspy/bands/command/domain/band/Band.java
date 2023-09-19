package com.qsspy.bands.command.domain.band;

import com.qsspy.commons.architecture.AggregateRoot;
import com.qsspy.commons.architecture.DomainValidationException;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;

import java.util.UUID;

@Builder(access = AccessLevel.PACKAGE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Band implements AggregateRoot {

    private final AggregateId id;
    private final BandName name;
    private final AdminId adminId;

    void validateCurrentState() {
        if(id == null) {
            throw new DomainValidationException("Id cannot be null");
        }
        if(name == null) {
            throw new DomainValidationException("Band name cannot be blank");
        }
        if(adminId == null) {
            throw new DomainValidationException("Admin id cannot be null");
        }

        id.validate();
        name.validate();
        adminId.validate();
    }

    public Snapshot takeSnapshot() {
        return Snapshot.builder()
                .id(id.value())
                .name(name.value())
                .adminId(adminId.value())
                .build();
    }
    @Builder
    public record Snapshot(
            UUID id,
            String name,
            UUID adminId
    ) { }

}
