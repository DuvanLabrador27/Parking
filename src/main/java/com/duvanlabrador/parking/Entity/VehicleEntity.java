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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "vehicle_id")
    private Long vehicleId;
    @Column(name = "license_plate", unique = true)
    private String licensePlate;
    @Column(name = "entry_time")
    private LocalDateTime entryTime;
    @Column(name = "departure_time")
    private LocalDateTime departureTime;
    @Column(name = "first_entry")
    private Boolean firstEntry;
    @ManyToOne(targetEntity = ParkingEntity.class)
    @JoinColumn(name = "parking_id")
    private ParkingEntity parking;


}
