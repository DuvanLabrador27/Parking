package com.duvanlabrador.parking.Converter;

import com.duvanlabrador.parking.DTO.RegisterDto;
import com.duvanlabrador.parking.DTO.VehicleDto;
import com.duvanlabrador.parking.Entity.RegisterEntity;
import com.duvanlabrador.parking.Entity.VehicleEntity;

public final class RegisterConverter {
    public static RegisterDto mapToDto(RegisterEntity registerEntity){
        RegisterDto register = new RegisterDto();
        register.setRegisterId(registerEntity.getRegisterId());
        register.setEntryTime(registerEntity.getEntryTime());
        register.setDepartureTime(registerEntity.getDepartureTime());

        return register;
    }

    public static RegisterEntity mapToEntity(RegisterDto registerDto){
        RegisterEntity register = new RegisterEntity();

        register.setEntryTime(registerDto.getEntryTime());
        register.setDepartureTime(registerDto.getDepartureTime());
        return register;
    }

}
