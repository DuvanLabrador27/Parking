package com.duvanlabrador.parking.Service.Impl;

import com.duvanlabrador.parking.DTO.ParkingDto;
import com.duvanlabrador.parking.DTO.VehicleDto;
import com.duvanlabrador.parking.Entity.ParkingEntity;
import com.duvanlabrador.parking.Entity.VehicleEntity;
import com.duvanlabrador.parking.Exception.NotFoundException;
import com.duvanlabrador.parking.Repository.ParkingRepository;
import com.duvanlabrador.parking.Repository.VehicleRepository;
import com.duvanlabrador.parking.Service.Interface.IVehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class VehicleServiceImpl implements IVehicleService {
    private final VehicleRepository vehicleRepository;
    @Autowired
    public VehicleServiceImpl(VehicleRepository vehicleRepository){
        this.vehicleRepository = vehicleRepository;
    }

    @Override
    public List<VehicleDto> getAllVehicles() {
        List<VehicleEntity> vehicleEntity = this.vehicleRepository.findAll();
        return vehicleEntity.stream().map(vehicle -> mapToDto(vehicle)).collect(Collectors.toList());
    }

    @Override
    public VehicleDto getVehicleById(Long vehicleId) {
        VehicleEntity vehicleEntity = this.vehicleRepository.findById(vehicleId)
                .orElseThrow(() -> new NotFoundException("Vehicle not found whit ID" + vehicleId));
        return mapToDto(vehicleEntity);
    }

    @Override
    public VehicleDto createVehicle(VehicleDto vehicleDto) {
        VehicleEntity vehicleEntity = mapToEntity(vehicleDto);
        VehicleEntity newVehicle = this.vehicleRepository.save(vehicleEntity);
        VehicleDto vehicle = mapToDto(newVehicle);
        return vehicle;
    }

    @Override
    public VehicleDto updateVehicle(Long vehicleId, VehicleDto vehicleDto) {
        VehicleEntity vehicleEntity = this.vehicleRepository.findById(vehicleId)
                .orElseThrow(() -> new NotFoundException("Vehicle not found whit ID" + vehicleId));
        vehicleEntity.setLicensePlate(vehicleDto.getLicensePlate());

        VehicleEntity updateVehicle = this.vehicleRepository.save(vehicleEntity);
        return mapToDto(updateVehicle);
    }

    @Override
    public void deleteVehicle(Long vehicleId) {
        VehicleEntity vehicleEntity = this.vehicleRepository.findById(vehicleId)
                .orElseThrow(() -> new NotFoundException("Vehicle not found whit ID" + vehicleId));
        this.vehicleRepository.delete(vehicleEntity);
    }

    public VehicleDto mapToDto(VehicleEntity vehicleEntity){
        VehicleDto vehicle = new VehicleDto();
        vehicle.setVehicleId(vehicleEntity.getVehicleId());
        vehicle.setLicensePlate(vehicleEntity.getLicensePlate());

        return vehicle;
    }

    public VehicleEntity mapToEntity(VehicleDto vehicleDto){
        VehicleEntity vehicle = new VehicleEntity();
        vehicle.setLicensePlate(vehicleDto.getLicensePlate());

        return vehicle;
    }
}
