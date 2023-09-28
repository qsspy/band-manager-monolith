package com.qsspy.domain.finances;

import com.qsspy.commons.architecture.ddd.AggregateRoot;
import com.qsspy.commons.architecture.ddd.DomainValidationException;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;

@Builder(access = AccessLevel.PACKAGE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Entity(name = "FINANCE_ENTRIES")
public class FinanceEntry implements AggregateRoot {

    @EmbeddedId
    private AggregateId id;

    @Embedded
    private BandId bandId;

    @Embedded
    private Amount amount;

    @Embedded
    @Nullable
    private Description description;

    @Embedded
    private Initiator initiator;

    @Column(name = "IS_OUTCOME")
    boolean isOutcome;

    void validateCurrentState() {
        if(id == null) {
            throw new DomainValidationException("Aggregate id cannot be null!");
        }
        if(bandId == null) {
            throw new DomainValidationException("Band id cannot be null!");
        }
        if(initiator == null) {
            throw new DomainValidationException("Initiator cannot be null!");
        }
        if(amount == null) {
            throw new DomainValidationException("Amount cannot be null!");
        }

        id.validate();
        bandId.validate();
        amount.validate();
        initiator.validate();
        if(description != null) {
            description.validate();
        }
    }
}
