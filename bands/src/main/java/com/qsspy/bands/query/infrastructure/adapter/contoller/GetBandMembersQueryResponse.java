package com.qsspy.bands.query.infrastructure.adapter.contoller;

import com.qsspy.commons.architecture.cqrs.QueryResult;
import lombok.Builder;

import java.util.List;
import java.util.UUID;

record GetBandMembersQueryResponse(
        List<BandMember> members
) implements QueryResult {

    @Builder
    public record BandMember(
            UUID id,
            String email,
            String firstName,
            String lastName
    ) {}
}
