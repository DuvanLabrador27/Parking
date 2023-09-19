package com.duvanlabrador.parking.Repository;

import com.duvanlabrador.parking.Entity.UserEntity;
import com.duvanlabrador.parking.Entity.VehicleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface VehicleRepository extends JpaRepository<VehicleEntity,String> {
    @Query(value = "SELECT * FROM vehicle v WHERE v.license_plate=:licensePlate", nativeQuery = true)
    VehicleEntity findByLicensePlate(@Param("licensePlate") String licensePlate);

}
