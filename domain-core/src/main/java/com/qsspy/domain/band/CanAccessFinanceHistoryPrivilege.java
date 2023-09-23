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
class CanAccessFinanceHistoryPrivilege implements ValueObject {

    @Column(name = "CAN_ACCESS_FINANCE_HISTORY")
    private boolean isAllowed;

    static CanAccessFinanceHistoryPrivilege from(final boolean isAllowed) {
        return new CanAccessFinanceHistoryPrivilege(isAllowed);
    }

    @Override
    public void validate() {
        //No need for validation
    }
}
