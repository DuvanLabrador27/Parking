package com.duvanlabrador.parking.Controller;

import com.duvanlabrador.parking.DTO.UserDto;
import com.duvanlabrador.parking.DTO.VehicleDto;
import com.duvanlabrador.parking.Exception.GeneralException;
import com.duvanlabrador.parking.Service.Interface.IVehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1")
public class VehicleController {

    private final IVehicleService vehicleService;

    @Autowired
    public VehicleController(IVehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }

    @GetMapping("vehicles")
    public List<VehicleDto> getAllVehicles(){
        return this.vehicleService.getAllVehicles();
    }
    @GetMapping("/vehicles/{licensePLate}")
    public ResponseEntity<VehicleDto> getVehicleById(@PathVariable String licensePLate){
        try {
            VehicleDto vehicle = this.vehicleService.getVehicleById(licensePLate);
            return new ResponseEntity<>(vehicle, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/createVehicle")
    public ResponseEntity<VehicleDto> registerEntry(@RequestBody VehicleDto vehicleDto) {

            VehicleDto vehicle = this.vehicleService.createVehicle(vehicleDto);
            return new ResponseEntity<>(vehicle, HttpStatus.CREATED);

    }




    @DeleteMapping("/deleteVehicle/{licensePLate}")
    public ResponseEntity<String> deleteVehicle(@PathVariable String licensePLate){
        try {
            this.vehicleService.deleteVehicle(licensePLate);
            return new ResponseEntity<>("The vehicle has been delete correctly", HttpStatus.ACCEPTED);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
