package com.qsspy.bands.command.infrastucture.port.repository;

import com.qsspy.bands.command.domain.band.Band;
import com.qsspy.bands.command.domain.band.BandMemberWithPrivileges;
import com.qsspy.jpadatamodel.entity.BandEntity;
import com.qsspy.jpadatamodel.entity.BandMemberPrivilegesEntity;
import com.qsspy.jpadatamodel.entity.DefaultBandPrivilegesEntity;
import com.qsspy.jpadatamodel.entity.UserEntity;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.UUID;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
class DomainToPersistentEntityMapper {

    static BandEntity toEntity(final Band.Snapshot bandSnapshot, final UserEntity userEntity) {

        final var bandEntity =  BandEntity.builder()
                .id(bandSnapshot.id())
                .name(bandSnapshot.name())
                .bandAdmin(userEntity)
                .defaultBandPrivileges(toDefaultBandPrivilegesEntity(bandSnapshot))
                .memberPrivileges(
                        bandSnapshot.bandMembersWithPrivileges().stream().map(DomainToPersistentEntityMapper::toBandMemberPrivilegesEntity).toList()
                )
                .build();

        userEntity.setOwnedBand(bandEntity);

        return bandEntity;
    }

    private static DefaultBandPrivilegesEntity toDefaultBandPrivilegesEntity(final Band.Snapshot bandSnapshot) {
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

    private static BandMemberPrivilegesEntity toBandMemberPrivilegesEntity(final BandMemberWithPrivileges.Snapshot snapshot) {
        return BandMemberPrivilegesEntity.builder()
                .bandId(snapshot.bandId())
                .memberId(snapshot.memberId())

                .canAccessCalendar(snapshot.canAccessCalendar())
                .canAddCalendarEntries(snapshot.canAddCalendarEntries())
                .canEditCalendarEntries(snapshot.canEditCalendarEntries())
                .canDeleteCalendarEntries(snapshot.canDeleteCalendarEntries())

                .canAccessFinanceHistory(snapshot.canAccessFinanceHistory())
                .canAddFinanceEntries(snapshot.canAddFinanceEntries())

                .canSeeFinanceIncomeEntries(snapshot.canSeeFinanceIncomeEntries())
                .canSeeFinanceOutcomeEntries(snapshot.canSeeFinanceOutcomeEntries())
                .build();
    }
}
