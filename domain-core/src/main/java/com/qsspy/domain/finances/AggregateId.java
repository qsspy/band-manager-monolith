package com.qsspy.domain.finances;

import com.qsspy.commons.architecture.ddd.DomainValidationException;
import com.qsspy.commons.architecture.ddd.ValueObject;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.UUID;

@Embeddable
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
class AggregateId implements ValueObject, Serializable {

    @Column(name = "ID")
    private UUID value;

    @Override
    public void validate() {
        if(value == null) {
            throw new DomainValidationException("Id cannot be null!");
        }
    }
}
