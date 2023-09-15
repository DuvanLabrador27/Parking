package com.duvanlabrador.parking.Service.Impl;

import com.duvanlabrador.parking.DTO.RegisterDto;
import com.duvanlabrador.parking.Repository.RegisterRepository;
import com.duvanlabrador.parking.Service.Interface.IRegisterService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RegisterServiceImpl implements IRegisterService {
   private final RegisterRepository registerRepository;
   @Autowired
   public RegisterServiceImpl(RegisterRepository registerRepository){
       this.registerRepository = registerRepository;
   }

    @Override
    public List<RegisterDto> getAllRegisters() {
        return null;
    }

    @Override
    public RegisterDto getRegisterById(Long registerId) {
        return null;
    }

    @Override
    public RegisterDto createRegister(RegisterDto registerDto) {
        return null;
    }

    @Override
    public RegisterDto updateRegister(Long registerId, RegisterDto registerDto) {
        return null;
    }

    @Override
    public void deleteRegisterDto(Long registerId) {

    }
}
