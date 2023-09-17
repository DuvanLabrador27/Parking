package com.duvanlabrador.parking.Service.Impl;

import com.duvanlabrador.parking.DTO.VehicleDto;
import com.duvanlabrador.parking.Entity.VehicleEntity;
import com.duvanlabrador.parking.Exception.GeneralException;
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
                .orElseThrow(() -> new GeneralException("Vehicle not found whit ID" + vehicleId));
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
                .orElseThrow(() -> new GeneralException("Vehicle not found whit ID" + vehicleId));
        vehicleEntity.setLicensePlate(vehicleDto.getLicensePlate());
        vehicleEntity.setEntryTime(vehicleDto.getEntryTime());
        vehicleEntity.setDepartureTime(vehicleDto.getDepartureTime());
        vehicleEntity.setFirstEntry(vehicleDto.getFirstEntry());

        VehicleEntity updateVehicle = this.vehicleRepository.save(vehicleEntity);
        return mapToDto(updateVehicle);
    }

    @Override
    public void deleteVehicle(Long vehicleId) {
        VehicleEntity vehicleEntity = this.vehicleRepository.findById(vehicleId)
                .orElseThrow(() -> new GeneralException("Vehicle not found whit ID" + vehicleId));
        this.vehicleRepository.delete(vehicleEntity);
    }

    public VehicleDto mapToDto(VehicleEntity vehicleEntity){
        VehicleDto vehicle = new VehicleDto();
        vehicle.setVehicleId(vehicleEntity.getVehicleId());
        vehicle.setLicensePlate(vehicleEntity.getLicensePlate());
        vehicle.setEntryTime(vehicleEntity.getEntryTime());
        vehicle.setDepartureTime(vehicleEntity.getDepartureTime());
        vehicle.setFirstEntry(vehicleEntity.getFirstEntry());

        return vehicle;
    }

    public VehicleEntity mapToEntity(VehicleDto vehicleDto){
        VehicleEntity vehicle = new VehicleEntity();
        vehicle.setLicensePlate(vehicleDto.getLicensePlate());
        vehicle.setEntryTime(vehicleDto.getEntryTime());
        vehicle.setDepartureTime(vehicleDto.getDepartureTime());
        vehicle.setFirstEntry(vehicleDto.getFirstEntry());

        return vehicle;
    }
}
