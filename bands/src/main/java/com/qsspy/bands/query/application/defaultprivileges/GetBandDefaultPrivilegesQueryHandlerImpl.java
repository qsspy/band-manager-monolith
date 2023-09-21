package com.qsspy.bands.query.application.defaultprivileges;

import com.qsspy.bands.query.application.defaultprivileges.port.input.GetBandDefaultPrivilegesQuery;
import com.qsspy.bands.query.application.defaultprivileges.port.input.GetBandDefaultPrivilegesQueryHandler;
import com.qsspy.bands.query.application.defaultprivileges.port.input.GetBandDefaultPrivilegesQueryResult;
import com.qsspy.bands.query.application.defaultprivileges.port.output.BandDefaultPrivilegesRepository;
import com.qsspy.bands.query.application.defaultprivileges.port.output.dto.BandDefaultPrivilegesDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
class GetBandDefaultPrivilegesQueryHandlerImpl implements GetBandDefaultPrivilegesQueryHandler {

    private final BandDefaultPrivilegesRepository repository;

    @Override
    public GetBandDefaultPrivilegesQueryResult handle(final GetBandDefaultPrivilegesQuery query) {
        query.validate();

        return repository.finBandDefaultPrivileges(query.bandId())
                .map(this::mapToResult)
                .orElseThrow(() -> new IllegalStateException("Could not find default privileges for the band"));
    }

    private GetBandDefaultPrivilegesQueryResult mapToResult(final BandDefaultPrivilegesDTO dto) {
        return GetBandDefaultPrivilegesQueryResult.builder()
                .canAccessCalendar(dto.canAccessCalendar())
                .canAddCalendarEntries(dto.canAddCalendarEntries())
                .canEditCalendarEntries(dto.canEditCalendarEntries())
                .canDeleteCalendarEntries(dto.canDeleteCalendarEntries())

                .canAccessFinanceHistory(dto.canAccessFinanceHistory())
                .canAddFinanceEntries(dto.canAddFinanceEntries())

                .canSeeFinanceIncomeEntries(dto.canSeeFinanceIncomeEntries())
                .canSeeFinanceOutcomeEntries(dto.canSeeFinanceOutcomeEntries())

                .canSeeCalendarEntryByDefault(dto.canSeeCalendarEntryByDefault())
                .canSeeCalendarEntryPaymentByDefault(dto.canSeeCalendarEntryPaymentByDefault())
                .canSeeCalendarEntryDetailsByDefault(dto.canSeeCalendarEntryDetailsByDefault())
                .build();
    }
}
