package com.qsspy.bands.command.domain.band;

import com.qsspy.bands.command.domain.band.dto.DefaultPrivilegesChangeSpecification;
import com.qsspy.bands.command.domain.band.dto.MemberPrivilegesChangeSpecification;
import com.qsspy.commons.architecture.ddd.AggregateRoot;
import com.qsspy.commons.architecture.cqrs.DomainValidationException;
import com.qsspy.commons.architecture.ddd.DomainException;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;

import java.util.List;
import java.util.UUID;

@Builder(access = AccessLevel.PACKAGE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Band implements AggregateRoot {

    private final AggregateId id;
    private final BandName name;
    private final AdminId adminId;
    private final DefaultBandPrivileges defaultBandPrivileges;
    private final List<BandMemberWithPrivileges> bandMembersWithPrivileges;

    public Band changeDefaultPrivileges(final DefaultPrivilegesChangeSpecification changeSpecification) {
        if(changeSpecification.canAccessCalendar() != null) {
            final var privilege = Privilege.from(changeSpecification.canAccessCalendar());
            if(!privilege.equals(defaultBandPrivileges.getCanAccessCalendar())) {
                defaultBandPrivileges.setCanAccessCalendar(privilege);
            }
        }
        if(changeSpecification.canAddCalendarEntries() != null) {
            final var privilege = Privilege.from(changeSpecification.canAddCalendarEntries());
            if(!privilege.equals(defaultBandPrivileges.getCanAddCalendarEntries())) {
                defaultBandPrivileges.setCanAddCalendarEntries(privilege);
            }
        }
        if(changeSpecification.canEditCalendarEntries() != null) {
            final var privilege = Privilege.from(changeSpecification.canEditCalendarEntries());
            if(!privilege.equals(defaultBandPrivileges.getCanEditCalendarEntries())) {
                defaultBandPrivileges.setCanEditCalendarEntries(privilege);
            }
        }
        if(changeSpecification.canDeleteCalendarEntries() != null) {
            final var privilege = Privilege.from(changeSpecification.canDeleteCalendarEntries());
            if(!privilege.equals(defaultBandPrivileges.getCanDeleteCalendarEntries())) {
                defaultBandPrivileges.setCanDeleteCalendarEntries(privilege);
            }
        }
        if(changeSpecification.canAccessFinanceHistory() != null) {
            final var privilege = Privilege.from(changeSpecification.canAccessFinanceHistory());
            if(!privilege.equals(defaultBandPrivileges.getCanAccessFinanceHistory())) {
                defaultBandPrivileges.setCanAccessFinanceHistory(privilege);
            }
        }
        if(changeSpecification.canAddFinanceEntries() != null) {
            final var privilege = Privilege.from(changeSpecification.canAddFinanceEntries());
            if(!privilege.equals(defaultBandPrivileges.getCanAddFinanceEntries())) {
                defaultBandPrivileges.setCanAddFinanceEntries(privilege);
            }
        }
        if(changeSpecification.canSeeFinanceIncomeEntries() != null) {
            final var privilege = Privilege.from(changeSpecification.canSeeFinanceIncomeEntries());
            if(!privilege.equals(defaultBandPrivileges.getCanSeeFinanceIncomeEntries())) {
                defaultBandPrivileges.setCanSeeFinanceIncomeEntries(privilege);
            }
        }
        if(changeSpecification.canSeeFinanceOutcomeEntries() != null) {
            final var privilege = Privilege.from(changeSpecification.canSeeFinanceOutcomeEntries());
            if(!privilege.equals(defaultBandPrivileges.getCanSeeFinanceOutcomeEntries())) {
                defaultBandPrivileges.setCanSeeFinanceOutcomeEntries(privilege);
            }
        }
        if(changeSpecification.canSeeCalendarEntryByDefault() != null) {
            final var privilege = Privilege.from(changeSpecification.canSeeCalendarEntryByDefault());
            if(!privilege.equals(defaultBandPrivileges.getCanSeeCalendarEntryByDefault())) {
                defaultBandPrivileges.setCanSeeCalendarEntryByDefault(privilege);
            }
        }
        if(changeSpecification.canSeeCalendarEntryPaymentByDefault() != null) {
            final var privilege = Privilege.from(changeSpecification.canSeeCalendarEntryPaymentByDefault());
            if(!privilege.equals(defaultBandPrivileges.getCanSeeCalendarEntryPaymentByDefault())) {
                defaultBandPrivileges.setCanSeeCalendarEntryPaymentByDefault(privilege);
            }
        }
        if(changeSpecification.canSeeCalendarEntryDetailsByDefault() != null) {
            final var privilege = Privilege.from(changeSpecification.canSeeCalendarEntryDetailsByDefault());
            if(!privilege.equals(defaultBandPrivileges.getCanSeeCalendarEntryDetailsByDefault())) {
                defaultBandPrivileges.setCanSeeCalendarEntryDetailsByDefault(privilege);
            }
        }

        return this;
    }

    public Band changeUserPrivileges(final MemberPrivilegesChangeSpecification changeSpecification, final UUID memberId) {

        if(bandMembersWithPrivileges.stream().noneMatch(member -> member.getMemberId().value().equals(memberId))) {
            throw new DomainException("Could not change privileges of member that is not part of a band");
        }

        final var member = bandMembersWithPrivileges.stream()
                .filter(bandMember -> bandMember.getMemberId().value().equals(memberId))
                .findFirst()
                .get();

        if(changeSpecification.canAccessCalendar() != null) {
            final var privilege = Privilege.from(changeSpecification.canAccessCalendar());
            if(!privilege.equals(member.getCanAccessCalendar())) {
                member.setCanAccessCalendar(privilege);
            }
        }
        if(changeSpecification.canAddCalendarEntries() != null) {
            final var privilege = Privilege.from(changeSpecification.canAddCalendarEntries());
            if(!privilege.equals(member.getCanAddCalendarEntries())) {
                member.setCanAddCalendarEntries(privilege);
            }
        }
        if(changeSpecification.canEditCalendarEntries() != null) {
            final var privilege = Privilege.from(changeSpecification.canEditCalendarEntries());
            if(!privilege.equals(member.getCanEditCalendarEntries())) {
                member.setCanEditCalendarEntries(privilege);
            }
        }
        if(changeSpecification.canDeleteCalendarEntries() != null) {
            final var privilege = Privilege.from(changeSpecification.canDeleteCalendarEntries());
            if(!privilege.equals(member.getCanDeleteCalendarEntries())) {
                member.setCanDeleteCalendarEntries(privilege);
            }
        }
        if(changeSpecification.canAccessFinanceHistory() != null) {
            final var privilege = Privilege.from(changeSpecification.canAccessFinanceHistory());
            if(!privilege.equals(member.getCanAccessFinanceHistory())) {
                member.setCanAccessFinanceHistory(privilege);
            }
        }
        if(changeSpecification.canAddFinanceEntries() != null) {
            final var privilege = Privilege.from(changeSpecification.canAddFinanceEntries());
            if(!privilege.equals(member.getCanAddFinanceEntries())) {
                member.setCanAddFinanceEntries(privilege);
            }
        }
        if(changeSpecification.canSeeFinanceIncomeEntries() != null) {
            final var privilege = Privilege.from(changeSpecification.canSeeFinanceIncomeEntries());
            if(!privilege.equals(member.getCanSeeFinanceIncomeEntries())) {
                member.setCanSeeFinanceIncomeEntries(privilege);
            }
        }
        if(changeSpecification.canSeeFinanceOutcomeEntries() != null) {
            final var privilege = Privilege.from(changeSpecification.canSeeFinanceOutcomeEntries());
            if(!privilege.equals(member.getCanSeeFinanceOutcomeEntries())) {
                member.setCanSeeFinanceOutcomeEntries(privilege);
            }
        }

        return this;
    }

    public Band addMember(final UUID userId) {
        if(userId.equals(adminId.value())) {
            throw new DomainException("Cannot add admin user to the band");
        }

        if(bandMembersWithPrivileges.stream().anyMatch(member -> member.getMemberId().value().equals(userId))) {
            throw new DomainException("Member is already assigned to this band!");
        }

        bandMembersWithPrivileges.add(
                BandMemberWithPrivileges.builder()
                        .bandId(new BandId(id.value()))
                        .memberId(new MemberId(userId))

                        .canAccessCalendar(defaultBandPrivileges.getCanAccessCalendar())
                        .canAddCalendarEntries(defaultBandPrivileges.getCanAddCalendarEntries())
                        .canEditCalendarEntries(defaultBandPrivileges.getCanEditCalendarEntries())
                        .canDeleteCalendarEntries(defaultBandPrivileges.getCanDeleteCalendarEntries())

                        .canAccessFinanceHistory(defaultBandPrivileges.getCanAccessFinanceHistory())
                        .canAddFinanceEntries(defaultBandPrivileges.getCanAddFinanceEntries())

                        .canSeeFinanceIncomeEntries(defaultBandPrivileges.getCanSeeFinanceIncomeEntries())
                        .canSeeFinanceOutcomeEntries(defaultBandPrivileges.getCanSeeFinanceOutcomeEntries())
                        .build()
        );

        return this;
    }

    public Band removeMember(final UUID userId) {
        if(userId.equals(adminId.value())) {
            throw new DomainException("Cannot remove admin user from the band");
        }

        if(bandMembersWithPrivileges.stream().noneMatch(member -> member.getMemberId().value().equals(userId))) {
            throw new DomainException("No such member in this company!");
        }

        bandMembersWithPrivileges.removeIf(member -> member.getMemberId().value().equals(userId));

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
        if(bandMembersWithPrivileges == null) {
            throw new DomainValidationException("Band members with privileges cannot be null");
        }

        id.validate();
        name.validate();
        adminId.validate();
        defaultBandPrivileges.validate();
        bandMembersWithPrivileges.forEach(BandMemberWithPrivileges::validate);
    }

    public Snapshot takeSnapshot() {
        return Snapshot.builder()
                .id(id.value())
                .name(name.value())
                .adminId(adminId.value())
                .defaultPrivileges(defaultBandPrivileges.takeSnapshot())
                .bandMembersWithPrivileges(
                        bandMembersWithPrivileges.stream().map(BandMemberWithPrivileges::takeSnapshot).toList()
                )
                .build();
    }
    @Builder
    public record Snapshot(
            UUID id,
            String name,
            UUID adminId,
            DefaultBandPrivileges.Snapshot defaultPrivileges,
            List<BandMemberWithPrivileges.Snapshot> bandMembersWithPrivileges
    ) { }
}
