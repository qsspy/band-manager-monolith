package com.qsspy.domain.calendar;

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
class Address implements ValueObject {

    @Column(name = "ADDRESS")
    private String fullAddress;

    @Override
    public void validate() {
        if(fullAddress == null || fullAddress.isBlank()) {
            throw new DomainValidationException("Full address cannot be blank");
        }
    }
}
