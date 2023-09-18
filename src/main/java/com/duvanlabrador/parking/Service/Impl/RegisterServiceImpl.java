package com.duvanlabrador.parking.Service.Impl;

import com.duvanlabrador.parking.Converter.RegisterConverter;
import com.duvanlabrador.parking.DTO.ParkingDto;
import com.duvanlabrador.parking.DTO.RegisterDto;
import com.duvanlabrador.parking.Entity.ParkingEntity;
import com.duvanlabrador.parking.Entity.RegisterEntity;
import com.duvanlabrador.parking.Entity.VehicleEntity;
import com.duvanlabrador.parking.Exception.GeneralException;
import com.duvanlabrador.parking.Repository.ParkingRepository;
import com.duvanlabrador.parking.Repository.RegisterRepository;
import com.duvanlabrador.parking.Repository.VehicleRepository;
import com.duvanlabrador.parking.Service.Interface.IRegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class RegisterServiceImpl implements IRegisterService {

    private final VehicleRepository vehicleRepository;
    private final ParkingRepository parkingRepository;

    private final RegisterRepository registerRepository;
    @Autowired
    public RegisterServiceImpl(VehicleRepository vehicleRepository,ParkingRepository parkingRepository,RegisterRepository registerRepository){
        this.vehicleRepository = vehicleRepository;
        this.parkingRepository = parkingRepository;
        this.registerRepository = registerRepository;
    }
    @Override
    public List<RegisterDto> getAllRegisterByVehicleAndParking(Long vehicleId, Long parkingId) {
        return null;
    }

    public RegisterDto createRegister(Long vehicleId, Long parkingId, RegisterDto registerDto) {
        VehicleEntity vehicle = vehicleRepository.findById(vehicleId)
                .orElseThrow(() -> new GeneralException("Vehicle not found with ID " + vehicleId));

        ParkingEntity parking = parkingRepository.findById(parkingId)
                .orElseThrow(() -> new GeneralException("Parking not found with ID " + parkingId));


        boolean vehicleAlreadyRegistered = registerRepository.existsByVehicleAndParking(vehicle, parking);

        if (vehicleAlreadyRegistered) {
            throw new GeneralException("Sorry, this vehicle is already registered in this parking lot.");
        }

        RegisterEntity register = new RegisterEntity();
        register.setEntryTime(LocalDateTime.now());
        register.setVehicle(vehicle);
        register.setParking(parking);

        RegisterEntity savedRegister = registerRepository.save(register);

        return RegisterConverter.mapToDto(savedRegister);
    }


}
