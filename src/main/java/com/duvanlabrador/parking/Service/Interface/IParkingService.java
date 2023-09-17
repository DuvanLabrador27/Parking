package com.duvanlabrador.parking.Service.Interface;

import com.duvanlabrador.parking.DTO.ParkingDto;

import java.util.List;

public interface IParkingService {
    public List<ParkingDto> getAllParking();
    public ParkingDto getParkingById(Long parkingId);
    public ParkingDto createParking(Long userId,ParkingDto parkingDto);
    public ParkingDto updateParking(Long parkingId, ParkingDto parkingDto);
    public void deleteParking(Long parkingId);
    public void openParking(Long parkingId);
    public void closeParking(Long parkingId);

}
