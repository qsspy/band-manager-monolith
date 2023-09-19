package com.qsspy.authservice.infrastructure.port.repository;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "USERS")
@Getter
class UserEntity {
    @Id
    @Column(name = "ID")
    private UUID id;

    @Column(name = "BAND_ID")
    private UUID bandId;

    @Column(name = "OWN_BAND_ID")
    private UUID ownBandId;

    @Column(name = "FIRST_NAME")
    private String firstName;

    @Column(name = "LAST_NAME")
    private String lastName;

    @Column(name = "EMAIL")
    private String email;

    @Column(name = "PASSWORD")
    private String password;
}
