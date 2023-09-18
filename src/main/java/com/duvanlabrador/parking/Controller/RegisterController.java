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


    @GetMapping("/registers/{vehicleId}/{parkingId}")
    public ResponseEntity<List<RegisterDto>> getRegisterByVehicleAndParking(
            @PathVariable Long vehicleId,
            @PathVariable Long parkingId) {

            List<RegisterDto> registers = registerService.getRegisterByVehicleAndParking(vehicleId, parkingId);
            return new ResponseEntity<>(registers, HttpStatus.OK);

    }

    @PostMapping("/register/vehicle/{vehicleId}/parking/{parkingId}")
    public ResponseEntity<RegisterDto> createRegister(
            @PathVariable Long vehicleId,
            @PathVariable Long parkingId,
            @RequestBody RegisterDto registerDto
    ) {
            RegisterDto createdRegister = registerService.createRegister(vehicleId, parkingId, registerDto);
            return ResponseEntity.ok(createdRegister);

    }

    @PostMapping("/exitVehicle/{vehicleId}/parking/{parkingId}")
    public ResponseEntity<?> exitVehicleToParking(
            @PathVariable Long vehicleId,
            @PathVariable Long parkingId
    ) {
            registerService.registerExit(vehicleId, parkingId);
            return ResponseEntity.ok("Vehicle exit registered successfully");

    }



}
