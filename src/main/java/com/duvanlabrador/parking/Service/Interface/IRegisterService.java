package com.duvanlabrador.parking.Service.Interface;

import com.duvanlabrador.parking.DTO.ParkingDto;
import com.duvanlabrador.parking.DTO.RegisterDto;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public interface IRegisterService {
    public List<RegisterDto> getRegisterByVehicleAndParking(String licensePlate, Long parkingId);
    public RegisterDto createRegister(String licensePlate,Long parkingId, RegisterDto registerDto);
    public void registerExit(String licensePlate, Long parkingId);

}
