package com.duvanlabrador.parking.Service.Impl;

import com.duvanlabrador.parking.Converter.ParkingConverter;
import com.duvanlabrador.parking.Converter.VehicleConverter;
import com.duvanlabrador.parking.DTO.VehicleDto;
import com.duvanlabrador.parking.Entity.ParkingEntity;
import com.duvanlabrador.parking.Entity.VehicleEntity;
import com.duvanlabrador.parking.Exception.GeneralException;
import com.duvanlabrador.parking.Exception.Message;
import com.duvanlabrador.parking.Repository.ParkingRepository;
import com.duvanlabrador.parking.Repository.VehicleRepository;
import com.duvanlabrador.parking.Service.Interface.IVehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class VehicleServiceImpl implements IVehicleService {
    private final VehicleRepository vehicleRepository;
    private final ParkingRepository parkingRepository;

    @Autowired
    public VehicleServiceImpl(VehicleRepository vehicleRepository, ParkingRepository parkingRepository) {
        this.vehicleRepository = vehicleRepository;
        this.parkingRepository = parkingRepository;

    }

    @Override
    public List<VehicleDto> getAllVehicles() {
        List<VehicleEntity> vehicleEntity = this.vehicleRepository.findAll();
        return vehicleEntity.stream().map(vehicle -> VehicleConverter.mapToDto(vehicle)).collect(Collectors.toList());
    }

    @Override
    public VehicleDto getVehicleById(String licensePLate) {
        VehicleEntity vehicleEntity = this.vehicleRepository.findByLicensePlate(licensePLate);

        return VehicleConverter.mapToDto(vehicleEntity);
    }

    @Override
    public VehicleDto createVehicle(VehicleDto vehicleDto) {
        String licensePlate = vehicleDto.getLicensePlate();

        if (validateLicensePlate(licensePlate)) {
            VehicleEntity vehicleEntity = VehicleConverter.mapToEntity(vehicleDto);
            VehicleEntity newVehicle = this.vehicleRepository.save(vehicleEntity);
            return VehicleConverter.mapToDto(newVehicle);
        } else {

            throw new GeneralException("The license plate doesn't meet the requirements.");
        }
    }
    public boolean validateLicensePlate(String licensePlate) {

        String regex = "^[a-zA-Z0-9]{6}$";

        return licensePlate.matches(regex);
    }


    @Override
    public void deleteVehicle(String licensePLate) {
        VehicleEntity vehicleEntity = this.vehicleRepository.findByLicensePlate(licensePLate);

        this.vehicleRepository.delete(vehicleEntity);
    }

}




