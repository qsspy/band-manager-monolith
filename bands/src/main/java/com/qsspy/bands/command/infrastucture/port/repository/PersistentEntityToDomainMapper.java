package com.qsspy.bands.command.infrastucture.port.repository;

import com.qsspy.bands.command.domain.band.Band;
import com.qsspy.bands.command.domain.band.BandFactory;
import com.qsspy.bands.command.domain.band.BandMemberWithPrivileges;
import com.qsspy.bands.command.domain.band.DefaultBandPrivileges;
import com.qsspy.jpadatamodel.entity.BandEntity;
import com.qsspy.jpadatamodel.entity.BandMemberPrivilegesEntity;
import com.qsspy.jpadatamodel.entity.DefaultBandPrivilegesEntity;
import com.qsspy.jpadatamodel.entity.UserEntity;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
class PersistentEntityToDomainMapper {

    static Band fromEntity(final BandEntity entity) {
        return BandFactory.instantiateFromSnapshot(
                Band.Snapshot.builder()
                        .id(entity.getId())
                        .name(entity.getName())
                        .adminId(entity.getBandAdmin().getId())
                        .defaultPrivileges(
                                DefaultBandPrivileges.Snapshot.builder()
                                        .id(entity.getDefaultBandPrivileges().getId())

                                        .canAccessCalendar(entity.getDefaultBandPrivileges().isCanAccessCalendar())
                                        .canAddCalendarEntries(entity.getDefaultBandPrivileges().isCanAddCalendarEntries())
                                        .canEditCalendarEntries(entity.getDefaultBandPrivileges().isCanEditCalendarEntries())
                                        .canDeleteCalendarEntries(entity.getDefaultBandPrivileges().isCanDeleteCalendarEntries())

                                        .canAccessFinanceHistory(entity.getDefaultBandPrivileges().isCanAccessFinanceHistory())
                                        .canAddFinanceEntries(entity.getDefaultBandPrivileges().isCanAddFinanceEntries())

                                        .canSeeFinanceIncomeEntries(entity.getDefaultBandPrivileges().isCanSeeFinanceIncomeEntries())
                                        .canSeeFinanceOutcomeEntries(entity.getDefaultBandPrivileges().isCanSeeFinanceIncomeEntries())

                                        .canSeeCalendarEntryByDefault(entity.getDefaultBandPrivileges().isCanSeeCalendarEntryByDefault())
                                        .canSeeCalendarEntryPaymentByDefault(entity.getDefaultBandPrivileges().isCanSeeCalendarEntryPaymentByDefault())
                                        .canSeeCalendarEntryDetailsByDefault(entity.getDefaultBandPrivileges().isCanSeeCalendarEntryDetailsByDefault())
                                        .build()
                        )
                        .bandMembersWithPrivileges(
                                entity.getMemberPrivileges().stream().map(PersistentEntityToDomainMapper::toSnapshot).toList()
                        )
                        .build()
        );
    }

    private static BandMemberWithPrivileges.Snapshot toSnapshot(final BandMemberPrivilegesEntity entity) {
        return BandMemberWithPrivileges.Snapshot.builder()
                .bandId(entity.getBandId())
                .memberId(entity.getMemberId())

                .canAccessCalendar(entity.isCanAccessCalendar())
                .canAddCalendarEntries(entity.isCanAddCalendarEntries())
                .canEditCalendarEntries(entity.isCanEditCalendarEntries())
                .canDeleteCalendarEntries(entity.isCanDeleteCalendarEntries())

                .canAccessFinanceHistory(entity.isCanAccessFinanceHistory())
                .canAddFinanceEntries(entity.isCanAddFinanceEntries())

                .canSeeFinanceIncomeEntries(entity.isCanSeeFinanceIncomeEntries())
                .canSeeFinanceOutcomeEntries(entity.isCanSeeFinanceIncomeEntries())
                .build();
    }
}
