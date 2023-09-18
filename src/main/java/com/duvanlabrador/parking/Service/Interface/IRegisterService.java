package com.duvanlabrador.parking.Service.Interface;

import com.duvanlabrador.parking.DTO.ParkingDto;
import com.duvanlabrador.parking.DTO.RegisterDto;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public interface IRegisterService {
    public List<RegisterDto> getAllRegisterByVehicleAndParking(Long vehicleId, Long parkingId);
    public RegisterDto createRegister(Long vehicleId,Long parkingId, RegisterDto registerDto);

}
