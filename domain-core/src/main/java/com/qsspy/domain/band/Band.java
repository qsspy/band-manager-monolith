package com.qsspy.domain.band;

import com.qsspy.commons.architecture.ddd.DomainException;
import com.qsspy.commons.architecture.ddd.DomainValidationException;
import com.qsspy.domain.band.dto.DefaultPrivilegesChangeSpecification;
import com.qsspy.domain.band.dto.MemberPrivilegesChangeSpecification;
import com.qsspy.domain.user.User;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Builder(access = AccessLevel.PACKAGE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Entity(name = "BANDS")
public class Band {
    @EmbeddedId
    private AggregateId id;

    @Embedded
    private BandName name;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "BAND_ADMIN_ID", referencedColumnName = "ID")
    private User adminUser;

    @OneToMany(mappedBy = "memberBand", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<User> bandMembers;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @PrimaryKeyJoinColumn(name = "BAND_ID", referencedColumnName = "ID")
    private DefaultBandPrivileges defaultBandPrivileges;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @PrimaryKeyJoinColumn(name = "BAND_ID", referencedColumnName = "ID")
    private List<BandMemberPrivileges> memberPrivileges;

    public Band changeDefaultPrivileges(final DefaultPrivilegesChangeSpecification changeSpecification) {
        if(changeSpecification.canAccessCalendar() != null) {
            final var privilege = CanAccessCalendarPrivilege.from(changeSpecification.canAccessCalendar());
            if(!privilege.equals(defaultBandPrivileges.getCanAccessCalendar())) {
                defaultBandPrivileges.setCanAccessCalendar(privilege);
            }
        }
        if(changeSpecification.canAddCalendarEntries() != null) {
            final var privilege = CanAddCalendarEntriesPrivilege.from(changeSpecification.canAddCalendarEntries());
            if(!privilege.equals(defaultBandPrivileges.getCanAddCalendarEntries())) {
                defaultBandPrivileges.setCanAddCalendarEntries(privilege);
            }
        }
        if(changeSpecification.canEditCalendarEntries() != null) {
            final var privilege = CanEditCalendarEntriesPrivilege.from(changeSpecification.canEditCalendarEntries());
            if(!privilege.equals(defaultBandPrivileges.getCanEditCalendarEntries())) {
                defaultBandPrivileges.setCanEditCalendarEntries(privilege);
            }
        }
        if(changeSpecification.canDeleteCalendarEntries() != null) {
            final var privilege = CanDeleteCalendarEntriesPrivilege.from(changeSpecification.canDeleteCalendarEntries());
            if(!privilege.equals(defaultBandPrivileges.getCanDeleteCalendarEntries())) {
                defaultBandPrivileges.setCanDeleteCalendarEntries(privilege);
            }
        }
        if(changeSpecification.canAccessFinanceHistory() != null) {
            final var privilege = CanAccessFinanceHistoryPrivilege.from(changeSpecification.canAccessFinanceHistory());
            if(!privilege.equals(defaultBandPrivileges.getCanAccessFinanceHistory())) {
                defaultBandPrivileges.setCanAccessFinanceHistory(privilege);
            }
        }
        if(changeSpecification.canAddFinanceEntries() != null) {
            final var privilege = CanAddFinanceEntriesPrivilege.from(changeSpecification.canAddFinanceEntries());
            if(!privilege.equals(defaultBandPrivileges.getCanAddFinanceEntries())) {
                defaultBandPrivileges.setCanAddFinanceEntries(privilege);
            }
        }
        if(changeSpecification.canSeeFinanceIncomeEntries() != null) {
            final var privilege = CanSeeFinanceIncomeEntriesPrivilege.from(changeSpecification.canSeeFinanceIncomeEntries());
            if(!privilege.equals(defaultBandPrivileges.getCanSeeFinanceIncomeEntries())) {
                defaultBandPrivileges.setCanSeeFinanceIncomeEntries(privilege);
            }
        }
        if(changeSpecification.canSeeFinanceOutcomeEntries() != null) {
            final var privilege = CanSeeFinanceOutcomeEntriesPrivilege.from(changeSpecification.canSeeFinanceOutcomeEntries());
            if(!privilege.equals(defaultBandPrivileges.getCanSeeFinanceOutcomeEntries())) {
                defaultBandPrivileges.setCanSeeFinanceOutcomeEntries(privilege);
            }
        }
        if(changeSpecification.canSeeCalendarEntryByDefault() != null) {
            final var privilege = CanSeeCalendarEntryByDefaultPrivilege.from(changeSpecification.canSeeCalendarEntryByDefault());
            if(!privilege.equals(defaultBandPrivileges.getCanSeeCalendarEntryByDefault())) {
                defaultBandPrivileges.setCanSeeCalendarEntryByDefault(privilege);
            }
        }
        if(changeSpecification.canSeeCalendarEntryPaymentByDefault() != null) {
            final var privilege = CanSeeCalendarEntryPaymentByDefaultPrivilege.from(changeSpecification.canSeeCalendarEntryPaymentByDefault());
            if(!privilege.equals(defaultBandPrivileges.getCanSeeCalendarEntryPaymentByDefault())) {
                defaultBandPrivileges.setCanSeeCalendarEntryPaymentByDefault(privilege);
            }
        }
        if(changeSpecification.canSeeCalendarEntryDetailsByDefault() != null) {
            final var privilege = CanSeeCalendarEntryDetailsByDefaultPrivilege.from(changeSpecification.canSeeCalendarEntryDetailsByDefault());
            if(!privilege.equals(defaultBandPrivileges.getCanSeeCalendarEntryDetailsByDefault())) {
                defaultBandPrivileges.setCanSeeCalendarEntryDetailsByDefault(privilege);
            }
        }

        return this;
    }

    public Band changeUserPrivileges(final MemberPrivilegesChangeSpecification changeSpecification, final UUID memberId) {

        if(memberPrivileges.stream().noneMatch(member -> member.getId().getMemberId().equals(memberId))) {
            throw new DomainException("Could not change privileges of member that is not part of a band");
        }

        final var member = memberPrivileges.stream()
                .filter(bandMember -> bandMember.getId().getMemberId().equals(memberId))
                .findFirst()
                .get();

        if(changeSpecification.canAccessCalendar() != null) {
            final var privilege = CanAccessCalendarPrivilege.from(changeSpecification.canAccessCalendar());
            if(!privilege.equals(member.getCanAccessCalendar())) {
                member.setCanAccessCalendar(privilege);
            }
        }
        if(changeSpecification.canAddCalendarEntries() != null) {
            final var privilege = CanAddCalendarEntriesPrivilege.from(changeSpecification.canAddCalendarEntries());
            if(!privilege.equals(member.getCanAddCalendarEntries())) {
                member.setCanAddCalendarEntries(privilege);
            }
        }
        if(changeSpecification.canEditCalendarEntries() != null) {
            final var privilege = CanEditCalendarEntriesPrivilege.from(changeSpecification.canEditCalendarEntries());
            if(!privilege.equals(member.getCanEditCalendarEntries())) {
                member.setCanEditCalendarEntries(privilege);
            }
        }
        if(changeSpecification.canDeleteCalendarEntries() != null) {
            final var privilege = CanDeleteCalendarEntriesPrivilege.from(changeSpecification.canDeleteCalendarEntries());
            if(!privilege.equals(member.getCanDeleteCalendarEntries())) {
                member.setCanDeleteCalendarEntries(privilege);
            }
        }
        if(changeSpecification.canAccessFinanceHistory() != null) {
            final var privilege = CanAccessFinanceHistoryPrivilege.from(changeSpecification.canAccessFinanceHistory());
            if(!privilege.equals(member.getCanAccessFinanceHistory())) {
                member.setCanAccessFinanceHistory(privilege);
            }
        }
        if(changeSpecification.canAddFinanceEntries() != null) {
            final var privilege = CanAddFinanceEntriesPrivilege.from(changeSpecification.canAddFinanceEntries());
            if(!privilege.equals(member.getCanAddFinanceEntries())) {
                member.setCanAddFinanceEntries(privilege);
            }
        }
        if(changeSpecification.canSeeFinanceIncomeEntries() != null) {
            final var privilege = CanSeeFinanceIncomeEntriesPrivilege.from(changeSpecification.canSeeFinanceIncomeEntries());
            if(!privilege.equals(member.getCanSeeFinanceIncomeEntries())) {
                member.setCanSeeFinanceIncomeEntries(privilege);
            }
        }
        if(changeSpecification.canSeeFinanceOutcomeEntries() != null) {
            final var privilege = CanSeeFinanceOutcomeEntriesPrivilege.from(changeSpecification.canSeeFinanceOutcomeEntries());
            if(!privilege.equals(member.getCanSeeFinanceOutcomeEntries())) {
                member.setCanSeeFinanceOutcomeEntries(privilege);
            }
        }

        return this;
    }

    public Band addMember(final User userToAdd) {
        if(userToAdd.getId().equals(adminUser.getId())) {
            throw new DomainException("Cannot add admin user to the band");
        }

        if(bandMembers.stream().anyMatch(member -> member.getId().equals(userToAdd.getId()))) {
            throw new DomainException("Member is already assigned to this band!");
        }

        final var privilegeId = new BandMemberPrivilegesId(id.getValue(), userToAdd.getId());
        if(memberPrivileges.stream().noneMatch(privilege -> privilege.getId().equals(privilegeId))) {
            memberPrivileges.add(
                    BandMemberPrivileges.builder()
                            .id(privilegeId)

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
        }

        userToAdd.setMemberBand(this);
        return this;
    }

    public Band removeMember(final UUID userId) {
        if(userId.equals(adminUser.getId())) {
            throw new DomainException("Cannot remove admin user from the band");
        }

        if(bandMembers.stream().noneMatch(member -> member.getId().equals(userId))) {
            throw new DomainException("No such member in this company!");
        }

        bandMembers.stream()
                .filter(member -> member.getId().equals(userId))
                .findFirst()
                .ifPresent(user -> {
                    user.setMemberBand(null);
                    bandMembers.remove(user);
                });

        return this;
    }

    void validateCurrentState() {
        if(id == null) {
            throw new DomainValidationException("Id cannot be null");
        }
        if(name == null) {
            throw new DomainValidationException("Band name cannot be blank");
        }
        if(adminUser == null) {
            throw new DomainValidationException("Admin user cannot be null");
        }
        if(defaultBandPrivileges == null) {
            throw new DomainValidationException("Default privileges cannot be null");
        }
        if(memberPrivileges == null) {
            throw new DomainValidationException("Band members with privileges cannot be null");
        }

        id.validate();
        name.validate();
        defaultBandPrivileges.validate();
        memberPrivileges.forEach(BandMemberPrivileges::validate);
    }
}
