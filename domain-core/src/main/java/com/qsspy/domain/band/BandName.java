package com.qsspy.domain.band;

import com.qsspy.commons.architecture.ddd.DomainValidationException;
import com.qsspy.commons.architecture.ddd.ValueObject;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Embeddable
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
class BandName implements ValueObject {

    @Column(name = "BAND_NAME")
    private String value;

    @Override
    public void validate() {
        if(value == null || value.isBlank()) {
            throw new DomainValidationException("Name cannot be blank!");
        }
    }
}
