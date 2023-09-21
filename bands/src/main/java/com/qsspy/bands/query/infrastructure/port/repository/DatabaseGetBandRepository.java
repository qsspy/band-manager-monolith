package com.qsspy.bands.query.infrastructure.port.repository;


import com.qsspy.bands.query.application.members.port.output.BandMembersGetRepository;
import com.qsspy.bands.query.application.members.port.output.dto.BandMemberDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
@RequiredArgsConstructor
class DatabaseGetBandRepository implements BandMembersGetRepository {

    private final JpaUserQueryRepository queryRepository;

    @Override
    public List<BandMemberDTO> findBandMembers(final UUID bandId) {
        return queryRepository.findBandMembersById(bandId);
    }
}
