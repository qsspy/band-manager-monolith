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
class CanAddFinanceEntriesPrivilege implements ValueObject {

    @Column(name = "CAN_ADD_FINANCE_ENTRIES")
    private boolean isAllowed;

    static CanAddFinanceEntriesPrivilege from(final boolean isAllowed) {
        return new CanAddFinanceEntriesPrivilege(isAllowed);
    }

    @Override
    public void validate() {
        //No need for validation
    }
}
