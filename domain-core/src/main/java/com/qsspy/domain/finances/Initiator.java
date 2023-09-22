package com.qsspy.domain.finances;

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
class Initiator implements ValueObject {

    @Column(name = "INITIATOR_EMAIL")
    private String email;

    @Override
    public void validate() {
        if(email == null || email.isBlank()) {
            throw new DomainValidationException("Initiator Email cannot be blank!");
        }
    }
}
