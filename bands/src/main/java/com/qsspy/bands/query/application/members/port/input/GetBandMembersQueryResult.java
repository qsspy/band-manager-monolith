package com.qsspy.bands.query.application.members.port.input;

import com.qsspy.commons.architecture.cqrs.Query;
import com.qsspy.commons.architecture.cqrs.QueryResult;
import com.qsspy.commons.architecture.cqrs.QueryValidationException;
import lombok.Builder;

import java.util.List;
import java.util.UUID;

public record GetBandMembersQueryResult(
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
