package com.qsspy.calendars.command.infrastructure.adapter.controller;

record RestrictMemberPrivilegesForEntryRequestBody(
        boolean canSeeCalendarEntry,
        boolean canSeeCalendarEntryPayment,
        boolean canSeeCalendarEntryDetails
) { }
