package com.qsspy.calendars.command.intrestructure.adapter.controller;

import com.qsspy.commons.architecture.cqrs.Command;
import com.qsspy.commons.architecture.cqrs.CommandValidationException;

import java.util.UUID;

record RestrictMemberPrivilegesForEntryRequestBody(
        boolean canSeeCalendarEntry,
        boolean canSeeCalendarEntryPayment,
        boolean canSeeCalendarEntryDetails
) { }
