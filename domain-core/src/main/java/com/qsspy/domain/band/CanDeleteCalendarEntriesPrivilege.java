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
class CanDeleteCalendarEntriesPrivilege implements ValueObject {

    @Column(name = "CAN_DELETE_CALENDAR_ENTRIES")
    private boolean isAllowed;

    static CanDeleteCalendarEntriesPrivilege from(final boolean isAllowed) {
        return new CanDeleteCalendarEntriesPrivilege(isAllowed);
    }

    @Override
    public void validate() {
        //No need for validation
    }
}
