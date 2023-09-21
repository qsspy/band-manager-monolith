package com.qsspy.calendars.command.domain.entry;

import com.qsspy.commons.architecture.ddd.DomainValidationException;
import com.qsspy.commons.architecture.ddd.Entity;
import lombok.*;

import java.util.UUID;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder(access = AccessLevel.PACKAGE)
@Getter(AccessLevel.PACKAGE)
@Setter(AccessLevel.PACKAGE)
public class RestrictedEntryViewerWithPrivileges implements Entity {

    private final EntryId entryId;
    private final MemberId memberId;

    private Privilege canSeeCalendarEntry;
    private Privilege canSeeCalendarEntryPayment;
    private Privilege canSeeCalendarEntryDetails;

    @Override
    public void validate() {
        if(entryId == null) {
            throw new DomainValidationException("Entry Id cannot be null!");
        }
        if(memberId == null) {
            throw new DomainValidationException("Member Id cannot be null!");
        }
        if(canSeeCalendarEntry == null) {
            throw new DomainValidationException("'canSeeCalendarEntry' privilege cannot be null!");
        }
        if(canSeeCalendarEntryPayment == null) {
            throw new DomainValidationException("'canSeeCalendarEntryPayment' privilege cannot be null!");
        }
        if(canSeeCalendarEntryDetails == null) {
            throw new DomainValidationException("'canSeeCalendarEntryDetails' privilege cannot be null!");
        }

        entryId.validate();
        memberId.validate();
        canSeeCalendarEntry.validate();
        canSeeCalendarEntryPayment.validate();
        canSeeCalendarEntryDetails.validate();
    }

    Snapshot takeSnapshot() {
        return Snapshot.builder()
                .entryId(entryId.value())
                .memberId(memberId.value())
                .canSeeCalendarEntry(canSeeCalendarEntry.isAllowed())
                .canSeeCalendarEntryPayment(canSeeCalendarEntryPayment.isAllowed())
                .canSeeCalendarEntryDetails(canSeeCalendarEntryDetails.isAllowed())
                .build();
    }

    @Builder
    public record Snapshot(
            UUID entryId,
            UUID memberId,
            boolean canSeeCalendarEntry,
            boolean canSeeCalendarEntryPayment,
            boolean canSeeCalendarEntryDetails
    ) {}
}
