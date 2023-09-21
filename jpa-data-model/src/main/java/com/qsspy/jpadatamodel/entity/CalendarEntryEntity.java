package com.qsspy.jpadatamodel.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.UUID;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "CALENDAR_ENTRIES")
@Getter
@Setter
public class CalendarEntryEntity {
    @Id
    @Column(name = "ID")
    private UUID id;

    @Column(name = "BAND_ID")
    private UUID bandId;

    @Column(name = "EVENT_DATE")
    LocalDateTime eventDate;

    @Column(name = "EVENT_KIND")
    String eventKind;

    @Column(name = "AMOUNT")
    BigDecimal amount;

    @Column(name = "ADDRESS")
    String address;

    @Column(name = "EVENT_DURATION")
    Duration eventDuration;

    @Column(name = "DESCRIPTION")
    String description;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "BAND_ID", referencedColumnName = "ID", insertable = false, updatable = false)
    private BandEntity band;
}
