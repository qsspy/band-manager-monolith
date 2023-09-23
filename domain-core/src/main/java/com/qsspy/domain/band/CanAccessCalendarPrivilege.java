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
class CanAccessCalendarPrivilege implements ValueObject {

    @Column(name = "CAN_ACCESS_CALENDAR")
    private boolean isAllowed;

    static CanAccessCalendarPrivilege from(final boolean isAllowed) {
        return new CanAccessCalendarPrivilege(isAllowed);
    }

    @Override
    public void validate() {
        //No need for validation
    }
}
