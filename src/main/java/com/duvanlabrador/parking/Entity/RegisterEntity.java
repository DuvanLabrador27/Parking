package com.duvanlabrador.parking.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "register")
@Data
@DynamicInsert
@DynamicUpdate
public class RegisterEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "register_id")
    private Long registerId;
    @Column(name = "entry_time")
    private LocalDateTime entryTime;
    @Column(name = "departure_time")
    private LocalDateTime departureTime;

    @ManyToOne(targetEntity = VehicleEntity.class)
    @JoinColumn(name = "license_plate")
    private VehicleEntity vehicle;

    @ManyToOne(targetEntity = ParkingEntity.class)
    @JoinColumn(name = "parking_id")
    private ParkingEntity parking;

}
