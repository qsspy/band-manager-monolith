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
class CanSeeFinanceOutcomeEntriesPrivilege implements ValueObject {

    @Column(name = "CAN_SEE_FINANCE_OUTCOME_ENTRIES")
    private boolean isAllowed;

    static CanSeeFinanceOutcomeEntriesPrivilege from(final boolean isAllowed) {
        return new CanSeeFinanceOutcomeEntriesPrivilege(isAllowed);
    }

    @Override
    public void validate() {
        //No need for validation
    }
}
