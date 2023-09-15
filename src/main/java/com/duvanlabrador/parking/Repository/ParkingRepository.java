package com.duvanlabrador.parking.Repository;

import com.duvanlabrador.parking.Entity.ParkingEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PutMapping;

@Repository
public interface ParkingRepository extends JpaRepository<ParkingEntity, Long> {
    @Transactional
    @Modifying
    @Query(value = "UPDATE parking p SET p.parking_status = true WHERE p.parking_id=:parkingId", nativeQuery = true)
    public void openParking(@Param("parkingId") Long parkingId);
    @Transactional
    @Modifying
    @Query(value = "UPDATE parking p SET p.parking_status = false WHERE p.parking_id=:parkingId", nativeQuery = true)
    public void closeParking(@Param("parkingId") Long parkingId);

}
