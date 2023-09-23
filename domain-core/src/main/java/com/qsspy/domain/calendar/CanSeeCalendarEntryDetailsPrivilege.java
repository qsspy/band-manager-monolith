package com.qsspy.domain.calendar;

import com.qsspy.commons.architecture.ddd.ValueObject;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Embeddable
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
class CanSeeCalendarEntryDetailsPrivilege implements ValueObject {

    @Column(name = "CAN_SEE_ENTRY_DETAILS")
    private boolean isAllowed;

    static CanSeeCalendarEntryDetailsPrivilege from(final boolean isAllowed) {
        return new CanSeeCalendarEntryDetailsPrivilege(isAllowed);
    }

    @Override
    public void validate() {
        //No need for validation
    }
}
