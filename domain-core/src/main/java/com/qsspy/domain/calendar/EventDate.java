package com.qsspy.domain.calendar;

import com.qsspy.commons.architecture.ddd.DomainValidationException;
import com.qsspy.commons.architecture.ddd.ValueObject;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Embeddable
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
class EventDate implements ValueObject {

    @Column(name = "EVENT_DATE")
    private LocalDateTime value;

    @Override
    public void validate() {
        if(value == null) {
            throw new DomainValidationException("Event date cannot be null!");
        }
    }
}
