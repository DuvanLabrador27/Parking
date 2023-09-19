package com.duvanlabrador.parking.Controller;

import com.duvanlabrador.parking.DTO.ParkingDto;
import com.duvanlabrador.parking.Service.Interface.IParkingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1")
public class ParkingController {
    private final IParkingService parkingService;
    @Autowired
    public ParkingController(IParkingService parkingService){
        this.parkingService = parkingService;
    }

    @GetMapping("/users/{userId}/parking")
    public List<ParkingDto> listCommentByUser(@PathVariable(value = "userId") Long userId){
        return parkingService.getAllParkingByUser(userId);
    }

    @GetMapping("/user/{userId}/parking/{parkingId}")
    public ResponseEntity<ParkingDto> getUserByParkingForId(
            @PathVariable(value = "userId") Long userId,
            @PathVariable(value = "parkingId") Long parkingId){

            ParkingDto parkingDto = this.parkingService.getParkingById(userId,parkingId);
            return new ResponseEntity<>(parkingDto,HttpStatus.OK);

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

    @PutMapping("/user/{userId}/parking/{parkingId}")
    public ResponseEntity<ParkingDto> updateParking(
            @PathVariable(value = "userId") Long userId,
            @RequestBody ParkingDto parkingDto,
            @PathVariable(value = "parkingId") Long parkingId
           ){

            ParkingDto parking = this.parkingService.updateParking(userId,parkingDto,parkingId);
            return new ResponseEntity<ParkingDto>(parking,HttpStatus.ACCEPTED);

    }

    @DeleteMapping("/user/{userId}/parking/{parkingId}")
    public ResponseEntity<String> deleteParking(
            @PathVariable(value = "userId") Long userId,
            @PathVariable(value = "parkingId") Long parkingId){

            this.parkingService.deleteParking(userId,parkingId);
            return new ResponseEntity<>("The parking has been delete correctly",HttpStatus.OK);

    }

}
