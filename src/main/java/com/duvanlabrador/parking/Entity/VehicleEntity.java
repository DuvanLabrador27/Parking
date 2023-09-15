package com.duvanlabrador.parking.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "vehicle")
public class VehicleEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "vehicle_id")
    private Long vehicleId;
    @Column(name = "license_plate", unique = true)
    private String licensePlate;
    @ManyToOne(targetEntity = ParkingEntity.class)
    @JoinColumn(name = "parking_id")
    private ParkingEntity parking;
    @OneToMany(
            targetEntity = RegisterEntity.class,
            mappedBy = "vehicle",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY
    )
    private Set<RegisterEntity> register = new HashSet<>();

}
