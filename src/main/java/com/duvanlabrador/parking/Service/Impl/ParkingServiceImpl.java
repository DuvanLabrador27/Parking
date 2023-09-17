package com.duvanlabrador.parking.Service.Impl;

import com.duvanlabrador.parking.Converter.ParkingConverter;
import com.duvanlabrador.parking.DTO.ParkingDto;
import com.duvanlabrador.parking.Entity.ParkingEntity;
import com.duvanlabrador.parking.Entity.UserEntity;
import com.duvanlabrador.parking.Exception.GeneralException;
import com.duvanlabrador.parking.Repository.ParkingRepository;
import com.duvanlabrador.parking.Repository.UserRepository;
import com.duvanlabrador.parking.Service.Interface.IParkingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ParkingServiceImpl implements IParkingService {
    private final ParkingRepository parkingRepository;
    private final UserRepository userRepository;
    @Autowired
    public ParkingServiceImpl(ParkingRepository parkingRepository, UserRepository userRepository){
        this.parkingRepository = parkingRepository;
        this.userRepository = userRepository;
    }

    @Override
    public List<ParkingDto> getAllParking() {
        List<ParkingEntity> parkingEntity = this.parkingRepository.findAll();
        return parkingEntity.stream().map(parking -> ParkingConverter.mapToDto(parking)).collect(Collectors.toList());
    }

    @Override
    public ParkingDto getParkingById(Long parkingId) {
        ParkingEntity parkingEntity = this.parkingRepository.findById(parkingId)
                .orElseThrow(() -> new GeneralException("Parking not found whit ID" + parkingId));
        return ParkingConverter.mapToDto(parkingEntity);
    }

    @Override
    public ParkingDto createParking(Long userId, ParkingDto parkingDto) {
        ParkingEntity parkingEntity = ParkingConverter.mapToEntity(parkingDto);
        UserEntity userEntity = this.userRepository.findById(userId).orElseThrow(() -> new GeneralException("User not found whit ID" + userId));
        parkingEntity.setUser(userEntity);
        ParkingEntity newParking = this.parkingRepository.save(parkingEntity);
        ParkingDto parking = ParkingConverter.mapToDto(newParking);
        return parking;
    }

    @Override
    public ParkingDto updateParking(Long parkingId, ParkingDto parkingDto) {
        ParkingEntity parkingEntity = this.parkingRepository.findById(parkingId)
                .orElseThrow(() -> new GeneralException("Parking not found whit ID" + parkingId));
        parkingEntity.setParkingName(parkingDto.getParkingName());
        parkingEntity.setParkingCapacity(parkingDto.getParkingCapacity());
        parkingEntity.setParkingPriceForHour(parkingDto.getParkingPriceForHour());
        parkingEntity.setParkingPriceForDay(parkingDto.getParkingPriceForDay());
        parkingEntity.setGetParkingPriceForMonth(parkingDto.getGetParkingPriceForMonth());
        parkingEntity.setAvailableSpace(parkingDto.getAvailableSpace());
        parkingEntity.setParkingStatus(parkingDto.getParkingStatus());

        ParkingEntity parking = this.parkingRepository.save(parkingEntity);
        return ParkingConverter.mapToDto(parking);
    }

    @Override
    public void deleteParking(Long parkingId) {
        ParkingEntity parkingEntity = this.parkingRepository.findById(parkingId)
                .orElseThrow(() -> new GeneralException("Parking not found whit ID" + parkingId));
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


}
