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
    @GetMapping("/vehicles/{vehicleId}")
    public ResponseEntity<VehicleDto> getVehicleById(@PathVariable Long vehicleId){
        try {
            VehicleDto vehicle = this.vehicleService.getVehicleById(vehicleId);
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


    @PutMapping("/updateVehicle/{vehicleId}")
    public ResponseEntity<VehicleDto> updateVehicle(@PathVariable Long vehicleId, @RequestBody VehicleDto vehicleDto){
        try {
            VehicleDto vehicle = this.vehicleService.updateVehicle(vehicleId,vehicleDto);
            return new ResponseEntity<>(vehicle,HttpStatus.ACCEPTED);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }

    }

    @DeleteMapping("/deleteVehicle/{vehicleId}")
    public ResponseEntity<String> deleteVehicle(@PathVariable Long vehicleId){
        try {
            this.vehicleService.deleteVehicle(vehicleId);
            return new ResponseEntity<>("The vehicle has been delete correctly", HttpStatus.ACCEPTED);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
