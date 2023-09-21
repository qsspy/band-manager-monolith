package com.qsspy.jpadatamodel.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Id;
import lombok.*;

import java.io.Serializable;
import java.util.UUID;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@EqualsAndHashCode
@Embeddable
public class RestrictedCalendarViewerPrivilegesEntityKey implements Serializable {
    @Id
    @Column(name = "ENTRY_ID")
    private UUID entryId;

    @Id
    @Column(name = "MEMBER_ID")
    private UUID memberId;
}
