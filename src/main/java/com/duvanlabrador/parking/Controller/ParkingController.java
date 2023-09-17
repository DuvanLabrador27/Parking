package com.duvanlabrador.parking.Controller;

import com.duvanlabrador.parking.DTO.ParkingDto;
import com.duvanlabrador.parking.Entity.ParkingEntity;
import com.duvanlabrador.parking.Service.Interface.IParkingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1")
public class ParkingController {
    private final IParkingService parkingService;
    @Autowired
    public ParkingController(IParkingService parkingService){
        this.parkingService = parkingService;
    }
    @PostMapping("/parking/{userId}")
    public ResponseEntity<ParkingDto> createdParking(
            @PathVariable(value = "userId") long userId,
            @RequestBody ParkingDto parkingDto){
        try{
            ParkingDto parking = parkingService.createParking(userId,parkingDto);
            return new ResponseEntity<ParkingDto>(parking, HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }
}
