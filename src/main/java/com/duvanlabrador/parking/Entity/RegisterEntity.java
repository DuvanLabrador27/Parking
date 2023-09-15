package com.duvanlabrador.parking.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "parking")
@Data
public class RegisterEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "register_id")
    private Long registerId;
    @Column(name = "entry_time")
    private LocalDateTime entryTime;
    @Column(name = "departure_time")
    private LocalDateTime departureTime;
    @Column(name = "first_entry")
    private Boolean firstEntry;
    @ManyToOne(targetEntity = VehicleEntity.class)
    @JoinColumn(name = "vehicle_id")
    private VehicleEntity vehicle;
}
