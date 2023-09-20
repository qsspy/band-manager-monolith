package com.qsspy.bands.command.domain.band;

import com.qsspy.bands.command.domain.band.dto.DefaultPrivilegesChangeSpecification;
import com.qsspy.commons.architecture.ddd.AggregateRoot;
import com.qsspy.commons.architecture.cqrs.DomainValidationException;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;

import java.util.UUID;

@Builder(access = AccessLevel.PACKAGE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Band implements AggregateRoot {

    private final AggregateId id;
    private final BandName name;
    private final AdminId adminId;
    private final DefaultBandPrivileges defaultBandPrivileges;
    private final List<>

    public Band changeDefaultPrivileges(final DefaultPrivilegesChangeSpecification changeSpecification) {
        if(changeSpecification.canAccessCalendar() != null) {
            final var privilege = Privilege.from(changeSpecification.canAccessCalendar());
            if(privilege.equals(defaultBandPrivileges.getCanAccessCalendar())) {
               return this;
            }
            defaultBandPrivileges.setCanAccessCalendar(privilege);
        }
        if(changeSpecification.canAddCalendarEntries() != null) {
            final var privilege = Privilege.from(changeSpecification.canAddCalendarEntries());
            if(privilege.equals(defaultBandPrivileges.getCanAddCalendarEntries())) {
                return this;
            }
            defaultBandPrivileges.setCanAddCalendarEntries(privilege);
        }
        if(changeSpecification.canEditCalendarEntries() != null) {
            final var privilege = Privilege.from(changeSpecification.canEditCalendarEntries());
            if(privilege.equals(defaultBandPrivileges.getCanEditCalendarEntries())) {
                return this;
            }
            defaultBandPrivileges.setCanEditCalendarEntries(privilege);
        }
        if(changeSpecification.canDeleteCalendarEntries() != null) {
            final var privilege = Privilege.from(changeSpecification.canDeleteCalendarEntries());
            if(privilege.equals(defaultBandPrivileges.getCanDeleteCalendarEntries())) {
                return this;
            }
            defaultBandPrivileges.setCanDeleteCalendarEntries(privilege);
        }
        if(changeSpecification.canAccessFinanceHistory() != null) {
            final var privilege = Privilege.from(changeSpecification.canAccessFinanceHistory());
            if(privilege.equals(defaultBandPrivileges.getCanAccessFinanceHistory())) {
                return this;
            }
            defaultBandPrivileges.setCanAccessFinanceHistory(privilege);
        }
        if(changeSpecification.canAddFinanceEntries() != null) {
            final var privilege = Privilege.from(changeSpecification.canAddFinanceEntries());
            if(privilege.equals(defaultBandPrivileges.getCanAddFinanceEntries())) {
                return this;
            }
            defaultBandPrivileges.setCanAddFinanceEntries(privilege);
        }
        if(changeSpecification.canSeeFinanceIncomeEntries() != null) {
            final var privilege = Privilege.from(changeSpecification.canSeeFinanceIncomeEntries());
            if(privilege.equals(defaultBandPrivileges.getCanSeeFinanceIncomeEntries())) {
                return this;
            }
            defaultBandPrivileges.setCanSeeFinanceIncomeEntries(privilege);
        }
        if(changeSpecification.canSeeFinanceOutcomeEntries() != null) {
            final var privilege = Privilege.from(changeSpecification.canSeeFinanceOutcomeEntries());
            if(privilege.equals(defaultBandPrivileges.getCanSeeFinanceOutcomeEntries())) {
                return this;
            }
            defaultBandPrivileges.setCanSeeFinanceOutcomeEntries(privilege);
        }
        if(changeSpecification.canSeeCalendarEntryByDefault() != null) {
            final var privilege = Privilege.from(changeSpecification.canSeeCalendarEntryByDefault());
            if(privilege.equals(defaultBandPrivileges.getCanSeeCalendarEntryByDefault())) {
                return this;
            }
            defaultBandPrivileges.setCanSeeCalendarEntryByDefault(privilege);
        }
        if(changeSpecification.canSeeCalendarEntryPaymentByDefault() != null) {
            final var privilege = Privilege.from(changeSpecification.canSeeCalendarEntryPaymentByDefault());
            if(privilege.equals(defaultBandPrivileges.getCanSeeCalendarEntryPaymentByDefault())) {
                return this;
            }
            defaultBandPrivileges.setCanSeeCalendarEntryPaymentByDefault(privilege);
        }
        if(changeSpecification.canSeeCalendarEntryDetailsByDefault() != null) {
            final var privilege = Privilege.from(changeSpecification.canSeeCalendarEntryDetailsByDefault());
            if(privilege.equals(defaultBandPrivileges.getCanSeeCalendarEntryDetailsByDefault())) {
                return this;
            }
            defaultBandPrivileges.setCanSeeCalendarEntryDetailsByDefault(privilege);
        }

        return this;
    }

    void validateCurrentState() {
        if(id == null) {
            throw new DomainValidationException("Id cannot be null");
        }
        if(name == null) {
            throw new DomainValidationException("Band name cannot be blank");
        }
        if(adminId == null) {
            throw new DomainValidationException("Admin id cannot be null");
        }
        if(defaultBandPrivileges == null) {
            throw new DomainValidationException("Default privileges cannot be null");
        }

        id.validate();
        name.validate();
        adminId.validate();
        defaultBandPrivileges.validate();
    }

    public Snapshot takeSnapshot() {
        return Snapshot.builder()
                .id(id.value())
                .name(name.value())
                .adminId(adminId.value())
                .defaultPrivileges(defaultBandPrivileges.takeSnapshot())
                .build();
    }
    @Builder
    public record Snapshot(
            UUID id,
            String name,
            UUID adminId,
            DefaultBandPrivileges.Snapshot defaultPrivileges
    ) { }
}
