package com.duvanlabrador.parking.Service.Interface;

import com.duvanlabrador.parking.DTO.VehicleDto;

import java.util.List;

public interface IVehicleService {
    public List<VehicleDto> getAllVehicles();
    public VehicleDto getVehicleById(Long vehicleId);
    public VehicleDto createVehicle(VehicleDto vehicleDto);
    public VehicleDto updateVehicle(Long vehicleId, VehicleDto vehicleDto);
    public void deleteVehicle(Long vehicleId);


}
