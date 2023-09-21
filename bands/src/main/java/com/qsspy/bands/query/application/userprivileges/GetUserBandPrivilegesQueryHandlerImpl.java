package com.qsspy.bands.query.application.userprivileges;

import com.qsspy.bands.query.application.defaultprivileges.port.input.GetBandDefaultPrivilegesQueryResult;
import com.qsspy.bands.query.application.defaultprivileges.port.output.dto.BandDefaultPrivilegesDTO;
import com.qsspy.bands.query.application.userprivileges.port.input.GetUserBandPrivilegesQuery;
import com.qsspy.bands.query.application.userprivileges.port.input.GetUserBandPrivilegesQueryHandler;
import com.qsspy.bands.query.application.userprivileges.port.input.GetUserBandPrivilegesQueryResult;
import com.qsspy.bands.query.application.userprivileges.port.output.BandMemberPrivilegesRepository;
import com.qsspy.bands.query.application.userprivileges.port.output.dto.BandMemberPrivilegesDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
class GetUserBandPrivilegesQueryHandlerImpl implements GetUserBandPrivilegesQueryHandler {

    private final BandMemberPrivilegesRepository repository;

    @Override
    public GetUserBandPrivilegesQueryResult handle(final GetUserBandPrivilegesQuery query) {
        query.validate();

        return repository.finBandMemberPrivileges(query.bandId(), query.memberId())
                .map(this::mapToResult)
                .orElseThrow(() -> new IllegalStateException("Could not find band member privileges"));
    }

    private GetUserBandPrivilegesQueryResult mapToResult(final BandMemberPrivilegesDTO dto) {
        return GetUserBandPrivilegesQueryResult.builder()
                .canAccessCalendar(dto.canAccessCalendar())
                .canAddCalendarEntries(dto.canAddCalendarEntries())
                .canEditCalendarEntries(dto.canEditCalendarEntries())
                .canDeleteCalendarEntries(dto.canDeleteCalendarEntries())

                .canAccessFinanceHistory(dto.canAccessFinanceHistory())
                .canAddFinanceEntries(dto.canAddFinanceEntries())

                .canSeeFinanceIncomeEntries(dto.canSeeFinanceIncomeEntries())
                .canSeeFinanceOutcomeEntries(dto.canSeeFinanceOutcomeEntries())
                .build();
    }
}
