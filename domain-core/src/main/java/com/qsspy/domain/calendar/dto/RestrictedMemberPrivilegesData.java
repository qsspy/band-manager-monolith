package com.qsspy.domain.calendar.dto;

import lombok.Builder;

import java.util.UUID;

@Builder
public record RestrictedMemberPrivilegesData(
        UUID memberId,
        boolean canSeeCalendarEntry,
        boolean canSeeCalendarEntryPayment,
        boolean canSeeCalendarEntryDetails
) { }
