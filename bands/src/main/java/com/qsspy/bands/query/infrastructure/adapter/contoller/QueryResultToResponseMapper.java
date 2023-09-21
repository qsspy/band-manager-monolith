package com.qsspy.bands.query.infrastructure.adapter.contoller;

import com.qsspy.bands.query.application.members.port.input.GetBandMembersQueryResult;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PACKAGE)
final class QueryResultToResponseMapper {

    static GetBandMembersQueryResponse toResponse(final GetBandMembersQueryResult result) {
        return new GetBandMembersQueryResponse(
                result.members().stream()
                        .map(member ->
                                GetBandMembersQueryResponse.BandMember.builder()
                                        .id(member.id())
                                        .email(member.email())
                                        .firstName(member.firstName())
                                        .lastName(member.lastName())
                                        .build()
                        )
                        .toList()
        );
    }
}
