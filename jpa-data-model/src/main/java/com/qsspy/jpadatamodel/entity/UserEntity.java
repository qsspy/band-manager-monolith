package com.qsspy.jpadatamodel.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "USERS")
@Getter
@Setter
public class UserEntity {
    @Id
    @Column(name = "ID")
    private UUID id;

    @Column(name = "FIRST_NAME")
    private String firstName;

    @Column(name = "LAST_NAME")
    private String lastName;

    @Column(name = "EMAIL")
    private String email;

    @Column(name = "PASSWORD")
    private String password;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "OWN_BAND_ID", referencedColumnName = "ID")
    private BandEntity ownedBand;

    @ManyToOne
    @JoinColumn(name="MEMBER_BAND_ID", referencedColumnName = "ID")
    private BandEntity memberBand;
}
