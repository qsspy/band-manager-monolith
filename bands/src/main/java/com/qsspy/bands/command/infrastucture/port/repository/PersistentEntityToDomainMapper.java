package com.qsspy.bands.command.infrastucture.port.repository;

import com.qsspy.bands.command.domain.band.Band;
import com.qsspy.bands.command.domain.band.BandFactory;
import com.qsspy.bands.command.domain.band.DefaultBandPrivileges;
import com.qsspy.jpadatamodel.entity.BandEntity;
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
                        .build()
        );
    }

    static BandEntity toEntity(final Band.Snapshot bandSnapshot, final UserEntity userEntity) {
        return BandEntity.builder()
                .id(bandSnapshot.id())
                .name(bandSnapshot.name())
                .bandAdmin(userEntity)
                .defaultBandPrivileges(toDefaultBandPrivilegesEntity(bandSnapshot))
                .build();
    }

    static DefaultBandPrivilegesEntity toDefaultBandPrivilegesEntity(final Band.Snapshot bandSnapshot) {
        return DefaultBandPrivilegesEntity.builder()
                .id(bandSnapshot.defaultPrivileges().id())
                .canAccessCalendar(bandSnapshot.defaultPrivileges().canAccessCalendar())
                .canAddCalendarEntries(bandSnapshot.defaultPrivileges().canAddCalendarEntries())
                .canEditCalendarEntries(bandSnapshot.defaultPrivileges().canEditCalendarEntries())
                .canDeleteCalendarEntries(bandSnapshot.defaultPrivileges().canDeleteCalendarEntries())

                .canAccessFinanceHistory(bandSnapshot.defaultPrivileges().canAccessFinanceHistory())
                .canAddFinanceEntries(bandSnapshot.defaultPrivileges().canAddFinanceEntries())

                .canSeeFinanceIncomeEntries(bandSnapshot.defaultPrivileges().canSeeFinanceIncomeEntries())
                .canSeeFinanceOutcomeEntries(bandSnapshot.defaultPrivileges().canSeeFinanceOutcomeEntries())

                .canSeeCalendarEntryByDefault(bandSnapshot.defaultPrivileges().canSeeCalendarEntryByDefault())
                .canSeeCalendarEntryPaymentByDefault(bandSnapshot.defaultPrivileges().canSeeCalendarEntryPaymentByDefault())
                .canSeeCalendarEntryDetailsByDefault(bandSnapshot.defaultPrivileges().canSeeCalendarEntryDetailsByDefault())
                .build();
    }
}
