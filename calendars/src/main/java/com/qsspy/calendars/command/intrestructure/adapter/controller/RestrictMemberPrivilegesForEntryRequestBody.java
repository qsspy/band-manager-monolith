package com.qsspy.calendars.command.intrestructure.adapter.controller;

record RestrictMemberPrivilegesForEntryRequestBody(
        boolean canSeeCalendarEntry,
        boolean canSeeCalendarEntryPayment,
        boolean canSeeCalendarEntryDetails
) { }
