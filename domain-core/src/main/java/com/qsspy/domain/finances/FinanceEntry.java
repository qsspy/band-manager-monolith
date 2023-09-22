package com.qsspy.domain.finances;

import com.qsspy.commons.architecture.ddd.AggregateRoot;
import com.qsspy.commons.architecture.ddd.DomainEntity;
import com.qsspy.commons.architecture.ddd.DomainValidationException;
import com.qsspy.jpadatamodel.entity.BandEntity;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.lang.Nullable;

import java.math.BigDecimal;
import java.util.UUID;

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
