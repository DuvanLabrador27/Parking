package com.duvanlabrador.parking.Controller;


import com.duvanlabrador.parking.DTO.RegisterDto;
import com.duvanlabrador.parking.Service.Interface.IRegisterService;
import com.duvanlabrador.parking.Service.Interface.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1")
public class RegisterController {
    private final IRegisterService registerService;


    @Autowired
    public RegisterController(@Qualifier("registerServiceImpl") IRegisterService registerService) {
        this.registerService = registerService;
    }

    @PostMapping("/register/vehicle/{vehicleId}/parking/{parkingId}")
    public ResponseEntity<RegisterDto> createRegister(
            @PathVariable Long vehicleId,
            @PathVariable Long parkingId,
            @RequestBody RegisterDto registerDto
    ) {
        try {
            RegisterDto createdRegister = registerService.createRegister(vehicleId, parkingId, registerDto);
            return ResponseEntity.ok(createdRegister);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }



}
