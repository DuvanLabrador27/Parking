package com.duvanlabrador.parking.DTO;

import com.duvanlabrador.parking.Entity.ParkingEntity;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VehicleDto {
    private Long vehicleId;
    private String licensePlate;

}
