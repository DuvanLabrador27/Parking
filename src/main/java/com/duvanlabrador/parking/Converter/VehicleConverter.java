package com.duvanlabrador.parking.Converter;

import com.duvanlabrador.parking.DTO.VehicleDto;
import com.duvanlabrador.parking.Entity.VehicleEntity;

public final class VehicleConverter {
    public static VehicleDto mapToDto(VehicleEntity vehicleEntity){
        VehicleDto vehicle = new VehicleDto();
        vehicle.setLicensePlate(vehicleEntity.getLicensePlate());


        return vehicle;
    }

    public static VehicleEntity mapToEntity(VehicleDto vehicleDto){
        VehicleEntity vehicle = new VehicleEntity();
        vehicle.setLicensePlate(vehicleDto.getLicensePlate());


        return vehicle;
    }
}
