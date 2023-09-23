package com.qsspy.domain.band;

import com.qsspy.commons.architecture.ddd.DomainValidationException;
import com.qsspy.commons.architecture.ddd.ValueObject;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.*;

import java.io.Serializable;
import java.util.UUID;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@EqualsAndHashCode
@Embeddable
class BandMemberPrivilegesId implements ValueObject, Serializable {
    @Column(name = "BAND_ID")
    private UUID bandId;

    @Column(name = "MEMBER_ID")
    private UUID memberId;

    @Override
    public void validate() {
        if(bandId == null) {
            throw new DomainValidationException("Band Id cannot be null");
        }
        if(memberId == null) {
            throw new DomainValidationException("Member Id cannot be null");
        }
    }
}
