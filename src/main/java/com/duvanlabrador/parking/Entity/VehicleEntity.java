package com.duvanlabrador.parking.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "vehicle")
@Data
public class VehicleEntity {
    @Id
    @Column(name = "license_plate", unique = true)
    private String licensePlate;
    @OneToMany(
            targetEntity = RegisterEntity.class,
            mappedBy = "vehicle",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY
    )
    private Set<RegisterEntity> register = new HashSet<>();


}
