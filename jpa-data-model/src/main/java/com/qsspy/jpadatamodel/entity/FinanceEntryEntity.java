package com.qsspy.jpadatamodel.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.lang.Nullable;

import java.math.BigDecimal;
import java.util.UUID;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "FINANCE_ENTRIES")
@Getter
@Setter
public class FinanceEntryEntity {
    @Id
    @Column(name = "ID")
    private UUID id;

    @Column(name = "BAND_ID")
    private UUID bandId;

    @Column(name = "AMOUNT")
    BigDecimal amount;

    @Column(name = "DESCRIPTION")
    String description;

    @Column(name = "INITIATOR_EMAIL")
    String initiatorEmail;

    @Column(name = "IS_OUTCOME")
    boolean isOutcome;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "BAND_ID", referencedColumnName = "ID", insertable = false, updatable = false)
    private BandEntity band;
}
