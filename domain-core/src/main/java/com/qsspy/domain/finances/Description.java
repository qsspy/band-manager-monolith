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
class Description implements ValueObject {

    @Column(name = "DESCRIPTION")
    private String text;

    @Override
    public void validate() {
        if(text == null || text.isBlank()) {
            throw new DomainValidationException("Description text cannot be blank!");
        }
    }
}
