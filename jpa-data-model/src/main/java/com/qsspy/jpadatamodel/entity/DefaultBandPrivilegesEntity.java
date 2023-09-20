package com.qsspy.jpadatamodel.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "DEFAULT_BAND_PRIVILEGES")
@Getter
public class DefaultBandPrivilegesEntity {
    @Id
    @Column(name = "BAND_ID")
    private UUID id;

    @Column(name = "CAN_ACCESS_CALENDAR")
    private boolean canAccessCalendar;

    @Column(name = "CAN_ADD_CALENDAR_ENTRIES")
    private boolean canAddCalendarEntries;

    @Column(name = "CAN_EDIT_CALENDAR_ENTRIES")
    private boolean canEditCalendarEntries;

    @Column(name = "CAN_DELETE_CALENDAR_ENTRIES")
    private boolean canDeleteCalendarEntries;

    @Column(name = "CAN_ACCESS_FINANCE_HISTORY")
    private boolean canAccessFinanceHistory;

    @Column(name = "CAN_ADD_FINANCE_ENTRIES")
    private boolean canAddFinanceEntries;

    @Column(name = "CAN_SEE_FINANCE_INCOME_ENTRIES")
    private boolean canSeeFinanceIncomeEntries;

    @Column(name = "CAN_SEE_FINANCE_OUTCOME_ENTRIES")
    private boolean canSeeFinanceOutcomeEntries;

    @Column(name = "CAN_SEE_CALENDAR_ENTRY_BY_DEFAULT")
    private boolean canSeeCalendarEntryByDefault;

    @Column(name = "CAN_SEE_CALENDAR_ENTRY_PAYMENT_BY_DEFAULT")
    private boolean canSeeCalendarEntryPaymentByDefault;

    @Column(name = "CAN_SEE_CALENDAR_ENTRY_DETAILS_BY_DEFAULT")
    private boolean canSeeCalendarEntryDetailsByDefault;

    @OneToOne(fetch = FetchType.LAZY)
    @PrimaryKeyJoinColumn(name = "BAND_ID", referencedColumnName = "ID")
    private BandEntity band;
}
