package com.qsspy.domain.band;

import com.qsspy.commons.architecture.ddd.ValueObject;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Embeddable
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
class CanSeeCalendarEntryDetailsByDefaultPrivilege implements ValueObject {

    @Column(name = "CAN_SEE_CALENDAR_ENTRY_DETAILS_BY_DEFAULT")
    private boolean isAllowed;

    static CanSeeCalendarEntryDetailsByDefaultPrivilege from(final boolean isAllowed) {
        return new CanSeeCalendarEntryDetailsByDefaultPrivilege(isAllowed);
    }

    @Override
    public void validate() {
        //No need for validation
    }
}
