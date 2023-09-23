package com.qsspy.domain.band;

import com.qsspy.commons.architecture.ddd.DomainValidationException;
import com.qsspy.commons.architecture.ddd.ValueObject;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.UUID;

@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PACKAGE)
class EntityId implements ValueObject, Serializable {

    @Column(name = "BAND_ID")
    private UUID value;

    @Override
    public void validate() {
        if(value == null) {
            throw new DomainValidationException("Id cannot be null!");
        }
    }
}
