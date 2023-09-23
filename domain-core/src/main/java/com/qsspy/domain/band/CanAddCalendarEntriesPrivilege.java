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
class CanAddCalendarEntriesPrivilege implements ValueObject {

    @Column(name = "CAN_ADD_CALENDAR_ENTRIES")
    private boolean isAllowed;

    static CanAddCalendarEntriesPrivilege from(final boolean isAllowed) {
        return new CanAddCalendarEntriesPrivilege(isAllowed);
    }

    @Override
    public void validate() {
        //No need for validation
    }
}
