package com.duvanlabrador.parking.Service.Impl;

import com.duvanlabrador.parking.DTO.ParkingDto;
import com.duvanlabrador.parking.Entity.ParkingEntity;
import com.duvanlabrador.parking.Exception.NotFoundException;
import com.duvanlabrador.parking.Repository.ParkingRepository;
import com.duvanlabrador.parking.Service.Interface.IParkingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ParkingServiceImpl implements IParkingService {
    private final ParkingRepository parkingRepository;
    @Autowired
    public ParkingServiceImpl(ParkingRepository parkingRepository){
        this.parkingRepository = parkingRepository;
    }

    @Override
    public List<ParkingDto> getAllParking() {
        List<ParkingEntity> parkingEntity = this.parkingRepository.findAll();
        return parkingEntity.stream().map(parking -> mapToDto(parking)).collect(Collectors.toList());
    }

    @Override
    public ParkingDto getParkingById(Long parkingId) {
        ParkingEntity parkingEntity = this.parkingRepository.findById(parkingId)
                .orElseThrow(() -> new NotFoundException("Parking not found whit ID" + parkingId));
        return mapToDto(parkingEntity);
    }

    @Override
    public ParkingDto createParking(ParkingDto parkingDto) {
        ParkingEntity parkingEntity = mapToEntity(parkingDto);
        ParkingEntity newParking = this.parkingRepository.save(parkingEntity);
        ParkingDto parking = mapToDto(newParking);
        return parking;
    }

    @Override
    public ParkingDto updateParking(Long parkingId, ParkingDto parkingDto) {
        ParkingEntity parkingEntity = this.parkingRepository.findById(parkingId)
                .orElseThrow(() -> new NotFoundException("Parking not found whit ID" + parkingId));
        parkingEntity.setParkingName(parkingDto.getParkingName());
        parkingEntity.setParkingCapacity(parkingDto.getParkingCapacity());
        parkingEntity.setParkingPriceForHour(parkingDto.getParkingPriceForHour());
        parkingEntity.setParkingPriceForDay(parkingDto.getParkingPriceForDay());
        parkingEntity.setGetParkingPriceForMonth(parkingDto.getGetParkingPriceForMonth());
        parkingEntity.setAvailableSpace(parkingDto.getAvailableSpace());
        parkingEntity.setParkingStatus(parkingDto.getParkingStatus());

        ParkingEntity parking = this.parkingRepository.save(parkingEntity);
        return mapToDto(parking);
    }

    @Override
    public void deleteParking(Long parkingId) {
        ParkingEntity parkingEntity = this.parkingRepository.findById(parkingId)
                .orElseThrow(() -> new NotFoundException("Parking not found whit ID" + parkingId));
        this.parkingRepository.delete(parkingEntity);
    }

    @Override
    public void openParking(Long parkingId) {
        this.parkingRepository.openParking(parkingId);
    }

    @Override
    public void closeParking(Long parkingId) {
        this.parkingRepository.closeParking(parkingId);
    }

    public ParkingDto mapToDto(ParkingEntity parkingEntity){
        ParkingDto parking = new ParkingDto();
        parking.setParkingId(parkingEntity.getParkingId());
        parking.setParkingName(parkingEntity.getParkingName());
        parking.setParkingCapacity(parkingEntity.getParkingCapacity());
        parking.setParkingPriceForHour(parkingEntity.getParkingPriceForHour());
        parking.setParkingPriceForDay(parkingEntity.getParkingPriceForDay());
        parking.setGetParkingPriceForMonth(parkingEntity.getGetParkingPriceForMonth());
        parking.setAvailableSpace(parkingEntity.getAvailableSpace());
        parking.setParkingStatus(parkingEntity.getParkingStatus());
        return parking;
    }

    public ParkingEntity mapToEntity(ParkingDto parkingDto){
        ParkingEntity parking = new ParkingEntity();
        parking.setParkingName(parkingDto.getParkingName());
        parking.setParkingCapacity(parkingDto.getParkingCapacity());
        parking.setParkingPriceForHour(parkingDto.getParkingPriceForHour());
        parking.setParkingPriceForDay(parkingDto.getParkingPriceForDay());
        parking.setGetParkingPriceForMonth(parkingDto.getGetParkingPriceForMonth());
        parking.setAvailableSpace(parkingDto.getAvailableSpace());
        parking.setParkingStatus(parkingDto.getParkingStatus());
        return parking;
    }
}
