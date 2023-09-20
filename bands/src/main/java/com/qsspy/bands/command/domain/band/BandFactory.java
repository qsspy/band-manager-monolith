package com.qsspy.bands.command.domain.band;

import com.qsspy.bands.command.domain.band.dto.BandCreationData;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.UUID;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Slf4j
public final class BandFactory {

    public static Band createrNewBand(final BandCreationData creationData) {
        final var id = UUID.randomUUID();
        final var band = Band.builder()
                .id(new AggregateId(id))
                .name(new BandName(creationData.bandName()))
                .adminId(new AdminId(creationData.creatorId()))
                .defaultBandPrivileges(DefaultBandPrivileges.newBaseDefaultPrivileges(new EntityId(id)))
                .build();

        band.validateCurrentState();

        log.info("Created band with id : {}", id);

        return band;
    }

    public static Band instantiateFromSnapshot(final Band.Snapshot snapshot) {
        final var band = Band.builder()
                .id(new AggregateId(snapshot.id()))
                .name(new BandName(snapshot.name()))
                .adminId(new AdminId(snapshot.adminId()))
                .defaultBandPrivileges(
                        DefaultBandPrivileges.builder()
                                .id(new EntityId(snapshot.defaultPrivileges().id()))
                                .canAccessCalendar(new Privilege(snapshot.defaultPrivileges().canAccessCalendar()))
                                .canAddCalendarEntries(new Privilege(snapshot.defaultPrivileges().canAddCalendarEntries()))
                                .canEditCalendarEntries(new Privilege(snapshot.defaultPrivileges().canEditCalendarEntries()))
                                .canDeleteCalendarEntries(new Privilege(snapshot.defaultPrivileges().canDeleteCalendarEntries()))
                                .canAccessFinanceHistory(new Privilege(snapshot.defaultPrivileges().canAccessFinanceHistory()))
                                .canAddFinanceEntries(new Privilege(snapshot.defaultPrivileges().canAddFinanceEntries()))
                                .canSeeFinanceIncomeEntries(new Privilege(snapshot.defaultPrivileges().canSeeFinanceIncomeEntries()))
                                .canSeeFinanceOutcomeEntries(new Privilege(snapshot.defaultPrivileges().canSeeFinanceOutcomeEntries()))
                                .canSeeCalendarEntryByDefault(new Privilege(snapshot.defaultPrivileges().canSeeCalendarEntryByDefault()))
                                .canSeeCalendarEntryPaymentByDefault(new Privilege(snapshot.defaultPrivileges().canSeeCalendarEntryPaymentByDefault()))
                                .canSeeCalendarEntryDetailsByDefault(new Privilege(snapshot.defaultPrivileges().canSeeCalendarEntryDetailsByDefault()))
                                .build()
                )
                .build();

        band.validateCurrentState();

        return band;
    }
}
