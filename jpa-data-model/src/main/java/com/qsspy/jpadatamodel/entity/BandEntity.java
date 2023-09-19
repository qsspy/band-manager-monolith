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

    @OneToOne(mappedBy = "ownedBand", cascade = CascadeType.ALL)
    private UserEntity bandAdmin;

    @OneToMany(mappedBy= "memberBand", fetch = FetchType.LAZY)
    private List<UserEntity> members;
}
