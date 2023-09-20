package com.qsspy.jpadatamodel.entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@EqualsAndHashCode
@Embeddable
public class BandMemberPrivilegesEntityKey implements Serializable {
    private UUID bandId;
    private UUID memberId;
}
