package com.duvanlabrador.parking.Service.Impl;

import com.duvanlabrador.parking.DTO.RegisterDto;
import com.duvanlabrador.parking.Entity.RegisterEntity;
import com.duvanlabrador.parking.Exception.NotFoundException;
import com.duvanlabrador.parking.Repository.RegisterRepository;
import com.duvanlabrador.parking.Service.Interface.IRegisterService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RegisterServiceImpl implements IRegisterService {
    private final RegisterRepository registerRepository;

    @Autowired
    public RegisterServiceImpl(RegisterRepository registerRepository) {
        this.registerRepository = registerRepository;
    }

    public List<RegisterDto> getAllRegisters() {
        List<RegisterEntity> registerEntity = this.registerRepository.findAll();
        return registerEntity.stream().map(register -> mapToDto(register)).collect(Collectors.toList());
    }

    @Override
    public RegisterDto getRegisterById(Long registerId) {
        RegisterEntity registerEntity = this.registerRepository.findById(registerId).orElseThrow(() -> new NotFoundException("Register not found whit ID" + registerId));
        return mapToDto(registerEntity);
    }

    @Override
    public RegisterDto createRegister(RegisterDto registerDto) {
        RegisterEntity registerEntity = mapToEntity(registerDto);
        RegisterEntity newRegister = this.registerRepository.save(registerEntity);
        RegisterDto register = mapToDto(newRegister);
        return register;
    }

    @Override
    public RegisterDto updateRegister(Long registerId, RegisterDto registerDto) {
        RegisterEntity registerEntity = this.registerRepository.findById(registerId).orElseThrow(() -> new NotFoundException("Register not 	found whit ID" + registerId));

        registerEntity.setEntryTime(registerDto.getEntryTime());
        registerEntity.setDepartureTime(registerDto.getDepartureTime());
        registerEntity.setFirstEntry(registerDto.getFirstEntry());

        RegisterEntity updateRegister = this.registerRepository.save(registerEntity);

        return mapToDto(updateRegister);

    }

    @Override
    public void deleteRegisterDto(Long registerId) {
        RegisterEntity register = this.registerRepository.findById(registerId).orElseThrow(() -> new NotFoundException("Register not found whit ID" + registerId));

        this.registerRepository.delete(register);
    }

    public RegisterDto mapToDto(RegisterEntity registerEntity) {
        RegisterDto registerDto = new RegisterDto();
        registerDto.setRegisterId(registerEntity.getRegisterId());
        registerDto.setEntryTime(registerEntity.getEntryTime());
        registerDto.setDepartureTime(registerEntity.getDepartureTime());
        registerDto.setFirstEntry(registerEntity.getFirstEntry());
        return registerDto;
    }

    public RegisterEntity mapToEntity(RegisterDto registerDto) {
        RegisterEntity registerEntity = new RegisterEntity();

        registerEntity.setEntryTime(registerDto.getEntryTime());
        registerEntity.setDepartureTime(registerDto.getDepartureTime());
        registerEntity.setFirstEntry(registerDto.getFirstEntry());
        return registerEntity;
    }

}
