package com.duvanlabrador.parking.Controller;


import com.duvanlabrador.parking.DTO.RegisterDto;
import com.duvanlabrador.parking.Exception.GeneralException;
import com.duvanlabrador.parking.Service.Interface.IRegisterService;
import com.duvanlabrador.parking.Service.Interface.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1")
public class RegisterController {
    private final IRegisterService registerService;


    @Autowired
    public RegisterController(@Qualifier("registerServiceImpl") IRegisterService registerService) {
        this.registerService = registerService;
    }


    @GetMapping("/registers/{licensePlate}/{parkingId}")
    public ResponseEntity<List<RegisterDto>> getRegisterByVehicleAndParking(
            @PathVariable String licensePlate,
            @PathVariable Long parkingId) {

            List<RegisterDto> registers = registerService.getRegisterByVehicleAndParking(licensePlate, parkingId);
            return new ResponseEntity<>(registers, HttpStatus.OK);

    }

    @PostMapping("/register/vehicle/{licensePlate}/parking/{parkingId}")
    public ResponseEntity<RegisterDto> createRegister(
            @PathVariable String licensePlate,
            @PathVariable Long parkingId,
            @RequestBody RegisterDto registerDto
    ) {
            RegisterDto createdRegister = registerService.createRegister(licensePlate, parkingId, registerDto);
            return ResponseEntity.ok(createdRegister);

    }

    @PostMapping("/exitVehicle/{licensePlate}/parking/{parkingId}")
    public ResponseEntity<?> exitVehicleToParking(
            @PathVariable String licensePlate,
            @PathVariable Long parkingId
    ) {
            registerService.registerExit(licensePlate, parkingId);
            return ResponseEntity.ok("Vehicle exit registered successfully");

    }



}
