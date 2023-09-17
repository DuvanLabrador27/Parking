package com.duvanlabrador.parking.Converter;

import com.duvanlabrador.parking.DTO.ParkingDto;
import com.duvanlabrador.parking.Entity.ParkingEntity;

public final class ParkingConverter {

    public static ParkingDto mapToDto(ParkingEntity parkingEntity){
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

    public static ParkingEntity mapToEntity(ParkingDto parkingDto){
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
