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
@Entity(name = "RESTRICTED_CALENDAR_ENTRY_VIEWER_PRIVILEGES")
@Getter
@IdClass(RestrictedCalendarViewerPrivilegesEntityKey.class)
public class RestrictedCalendarViewerPrivilegesEntity {

    @Id
    @Column(name = "ENTRY_ID")
    private UUID entryId;

    @Id
    @Column(name = "MEMBER_ID")
    private UUID memberId;

    @Column(name = "CAN_SEE_ENTRY")
    boolean canSeeCalendarEntry;

    @Column(name = "CAN_SEE_ENTRY_PAYMENT")
    boolean canSeeCalendarEntryPayment;

    @Column(name = "CAN_SEE_ENTRY_DETAILS")
    boolean canSeeCalendarEntryDetails;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ENTRY_ID", referencedColumnName = "ID", insertable = false, updatable = false)
    private CalendarEntryEntity entry;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MEMBER_ID", referencedColumnName = "ID", insertable = false, updatable = false)
    private UserEntity member;
}
