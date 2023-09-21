package com.qsspy.bands.query.infrastructure.port.repository;


import com.qsspy.bands.query.application.defaultprivileges.port.output.BandDefaultPrivilegesRepository;
import com.qsspy.bands.query.application.defaultprivileges.port.output.dto.BandDefaultPrivilegesDTO;
import com.qsspy.bands.query.application.members.port.output.BandMembersGetRepository;
import com.qsspy.bands.query.application.members.port.output.dto.BandMemberDTO;
import com.qsspy.bands.query.application.userprivileges.port.output.BandMemberPrivilegesRepository;
import com.qsspy.bands.query.application.userprivileges.port.output.dto.BandMemberPrivilegesDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
@RequiredArgsConstructor
class DatabaseGetBandRepository implements BandMembersGetRepository, BandDefaultPrivilegesRepository, BandMemberPrivilegesRepository {

    private final JpaUserQueryRepository userQueryRepository;
    private final JpaDefaultBandPrivilegesQueryRepository defaultBandPrivilegesQueryRepository;
    private final JpaBandMemberPrivilegesQueryRepository bandMemberPrivilegesQueryRepository;

    @Override
    public List<BandMemberDTO> findBandMembers(final UUID bandId) {
        return userQueryRepository.findBandMembersById(bandId);
    }

    @Override
    public Optional<BandDefaultPrivilegesDTO> finBandDefaultPrivileges(final UUID bandID) {
        return defaultBandPrivilegesQueryRepository.findBandDefaultPrivileges(bandID);
    }

    @Override
    public Optional<BandMemberPrivilegesDTO> finBandMemberPrivileges(final UUID bandId, final UUID memberId) {
        return bandMemberPrivilegesQueryRepository.findBandMemberPrivileges(bandId, memberId);
    }
}
