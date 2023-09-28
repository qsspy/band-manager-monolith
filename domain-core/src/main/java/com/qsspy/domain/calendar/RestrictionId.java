package com.qsspy.domain.calendar;

import com.qsspy.commons.architecture.ddd.DomainValidationException;
import com.qsspy.commons.architecture.ddd.ValueObject;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.*;

import java.io.Serializable;
import java.util.UUID;

@Embeddable
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter(AccessLevel.PACKAGE)
@EqualsAndHashCode
class RestrictionId implements ValueObject, Serializable {

    @Column(name = "ENTRY_ID")
    private UUID entryId;

    @Column(name = "MEMBER_ID")
    private UUID memberId;

    @Override
    public void validate() {
        if(entryId == null) {
            throw new DomainValidationException("Entry id cannot be null!");
        }
        if(memberId == null) {
            throw new DomainValidationException("Member id cannot be null!");
        }
    }
}
