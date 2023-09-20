package com.qsspy.bands.command.infrastucture.port.repository;

import com.qsspy.bands.command.domain.band.Band;
import com.qsspy.jpadatamodel.entity.BandEntity;
import com.qsspy.jpadatamodel.entity.DefaultBandPrivilegesEntity;
import com.qsspy.jpadatamodel.entity.UserEntity;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.UUID;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
class DomainToPersistentEntityMapper {

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
                .canEditCalendarEntries(bandSnapshot.defaultPrivileges().canAddCalendarEntries())
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
