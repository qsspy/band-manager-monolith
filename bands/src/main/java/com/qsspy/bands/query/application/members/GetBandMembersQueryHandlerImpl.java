package com.qsspy.bands.query.application.members;

import com.qsspy.bands.query.application.members.port.input.GetBandMembersQuery;
import com.qsspy.bands.query.application.members.port.input.GetBandMembersQueryHandler;
import com.qsspy.bands.query.application.members.port.input.GetBandMembersQueryResult;
import com.qsspy.bands.query.application.members.port.output.BandMembersGetRepository;
import com.qsspy.bands.query.application.members.port.output.dto.BandMemberDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
class GetBandMembersQueryHandlerImpl implements GetBandMembersQueryHandler {

    private final BandMembersGetRepository bandMembersRepository;

    @Override
    public GetBandMembersQueryResult handle(final GetBandMembersQuery query) {
        query.validate();

        final var bandMembers = bandMembersRepository
                .findBandMembers(query.bandId())
                .stream()
                .map(this::toResultMember)
                .toList();

        return new GetBandMembersQueryResult(bandMembers);
    }

    private GetBandMembersQueryResult.BandMember toResultMember(final BandMemberDTO dtoMember) {
        return GetBandMembersQueryResult.BandMember.builder()
                .id(dtoMember.id())
                .email(dtoMember.email())
                .firstName(dtoMember.firstName())
                .lastName(dtoMember.lastName())
                .build();
    }
}
