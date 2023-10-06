package com.qsspy.bands.command.infrastructure.adapter.controller;

import java.util.UUID;

record RemoveBandMemberRequestBody(
        UUID userId
) {
}
