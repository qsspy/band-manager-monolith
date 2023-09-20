package com.qsspy.jpadatamodel.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "BAND_MEMBER_PRIVILEGES")
@Getter
@IdClass(BandMemberPrivilegesEntityKey.class)
public class BandMemberPrivilegesEntity {

    @Id
    @Column(name = "BAND_ID")
    private UUID bandId;

    @Id
    @Column(name = "MEMBER_ID")
    private UUID memberId;

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

    @ManyToOne(fetch = FetchType.LAZY)
    @PrimaryKeyJoinColumn(name = "BAND_ID", referencedColumnName = "ID")
    private BandEntity band;

    @ManyToOne(fetch = FetchType.LAZY)
    @PrimaryKeyJoinColumn(name = "MEMBER_ID", referencedColumnName = "ID")
    private UserEntity member;
}
