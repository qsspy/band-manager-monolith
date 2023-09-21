package com.qsspy.bands.query.infrastructure.adapter.contoller;

import com.qsspy.bands.query.application.defaultprivileges.port.input.GetBandDefaultPrivilegesQueryResult;
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
    
    static GetBandDefaultPrivilegesQueryResponse toResponse(final GetBandDefaultPrivilegesQueryResult result) {
        return GetBandDefaultPrivilegesQueryResponse.builder()
                .canAccessCalendar(result.canAccessCalendar())
                .canAddCalendarEntries(result.canAddCalendarEntries())
                .canEditCalendarEntries(result.canEditCalendarEntries())
                .canDeleteCalendarEntries(result.canDeleteCalendarEntries())

                .canAccessFinanceHistory(result.canAccessFinanceHistory())
                .canAddFinanceEntries(result.canAddFinanceEntries())

                .canSeeFinanceIncomeEntries(result.canSeeFinanceIncomeEntries())
                .canSeeFinanceOutcomeEntries(result.canSeeFinanceOutcomeEntries())

                .canSeeCalendarEntryByDefault(result.canSeeCalendarEntryByDefault())
                .canSeeCalendarEntryPaymentByDefault(result.canSeeCalendarEntryPaymentByDefault())
                .canSeeCalendarEntryDetailsByDefault(result.canSeeCalendarEntryDetailsByDefault())
                .build();
    }
}
