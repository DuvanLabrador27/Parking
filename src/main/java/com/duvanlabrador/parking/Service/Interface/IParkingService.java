package com.duvanlabrador.parking.Service.Interface;

import com.duvanlabrador.parking.DTO.ParkingDto;

import java.util.List;

public interface IParkingService {
    public List<ParkingDto> getAllParkingByUser(Long userId);
    public ParkingDto getParkingById(Long userId, Long parkingId);
    public ParkingDto createParking(Long userId,ParkingDto parkingDto);
    public ParkingDto updateParking(Long userId, ParkingDto parkingDto,Long parkingId);
    public void deleteParking(Long userId, Long parkingId);
    public void openParking(Long parkingId);
    public void closeParking(Long parkingId);

}
