package com.duvanlabrador.parking.Service.Interface;

import com.duvanlabrador.parking.DTO.VehicleDto;

import java.util.List;

public interface IVehicleService {
    public List<VehicleDto> getAllVehicles();
    public VehicleDto getVehicleById(String licensePLate);
    public VehicleDto createVehicle(VehicleDto vehicleDto);
    public void deleteVehicle(String licensePLate);


}
