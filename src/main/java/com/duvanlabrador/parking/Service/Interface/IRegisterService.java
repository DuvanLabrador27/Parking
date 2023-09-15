package com.duvanlabrador.parking.Service.Interface;

import com.duvanlabrador.parking.DTO.RegisterDto;
import com.duvanlabrador.parking.DTO.VehicleDto;

import java.util.List;

public interface IRegisterService {
    public List<RegisterDto> getAllRegisters();
    public RegisterDto getRegisterById(Long registerId);
    public RegisterDto createRegister(RegisterDto registerDto);
    public RegisterDto updateRegister(Long registerId, RegisterDto registerDto);
    public void deleteRegisterDto(Long registerId);
}
