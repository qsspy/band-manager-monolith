package com.qsspy.domain.calendar;

import com.qsspy.commons.architecture.ddd.DomainValidationException;
import com.qsspy.commons.architecture.ddd.ValueObject;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.time.Duration;

@Embeddable
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
class EventDuration implements ValueObject {

    @Column(name = "EVENT_DURATION")
    private Duration value;

    @Override
    public void validate() {
        if(value == null) {
            throw new DomainValidationException("Event duration cannot be null!");
        }
    }
}
