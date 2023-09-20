package com.qsspy.jpadatamodel.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "BANDS")
@Getter
public class BandEntity {
    @Id
    @Column(name = "ID")
    private UUID id;

    @Column(name = "BAND_NAME")
    private String name;

    @OneToOne(mappedBy = "ownedBand", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private UserEntity bandAdmin;

    @OneToOne(mappedBy = "band", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private DefaultBandPrivilegesEntity defaultBandPrivileges;

    @OneToMany(mappedBy = "band", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<BandMemberPrivilegesEntity> memberPrivileges;
}
