package com.qsspy.bands.query.infrastructure.port.repository;


import com.qsspy.bands.query.application.defaultprivileges.port.output.BandDefaultPrivilegesRepository;
import com.qsspy.bands.query.application.defaultprivileges.port.output.dto.BandDefaultPrivilegesDTO;
import com.qsspy.bands.query.application.members.port.output.BandMembersGetRepository;
import com.qsspy.bands.query.application.members.port.output.dto.BandMemberDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
@RequiredArgsConstructor
class DatabaseGetBandRepository implements BandMembersGetRepository, BandDefaultPrivilegesRepository {

    private final JpaUserQueryRepository userQueryRepository;
    private final JpaDefaultBandPrivilegesQueryRepository defaultBandPrivilegesQueryRepository;

    @Override
    public List<BandMemberDTO> findBandMembers(final UUID bandId) {
        return userQueryRepository.findBandMembersById(bandId);
    }

    @Override
    public Optional<BandDefaultPrivilegesDTO> finBandDefaultPrivileges(final UUID bandID) {
        return defaultBandPrivilegesQueryRepository.findBandDefaultPrivileges(bandID);
    }
}
