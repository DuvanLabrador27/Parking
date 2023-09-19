package com.duvanlabrador.parking.Repository;

import com.duvanlabrador.parking.Entity.ParkingEntity;
import com.duvanlabrador.parking.Entity.RegisterEntity;
import com.duvanlabrador.parking.Entity.VehicleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RegisterRepository extends JpaRepository<RegisterEntity, Long> {
    boolean existsByVehicleAndParking(VehicleEntity vehicle, ParkingEntity parking);
    List<RegisterEntity> findAllByVehicleLicensePlateAndParkingParkingId(String licensePlate, Long parkingId);

    Optional<RegisterEntity> findByVehicleAndParkingAndDepartureTimeIsNull(VehicleEntity vehicle, ParkingEntity parking);


}
