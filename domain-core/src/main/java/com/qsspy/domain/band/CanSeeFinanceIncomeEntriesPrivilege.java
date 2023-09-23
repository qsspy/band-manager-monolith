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
class CanSeeFinanceIncomeEntriesPrivilege implements ValueObject {

    @Column(name = "CAN_SEE_FINANCE_INCOME_ENTRIES")
    private boolean isAllowed;

    static CanSeeFinanceIncomeEntriesPrivilege from(final boolean isAllowed) {
        return new CanSeeFinanceIncomeEntriesPrivilege(isAllowed);
    }

    @Override
    public void validate() {
        //No need for validation
    }
}
