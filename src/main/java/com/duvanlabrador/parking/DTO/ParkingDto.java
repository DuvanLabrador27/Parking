package com.duvanlabrador.parking.DTO;

import com.duvanlabrador.parking.Util.ParkingStatus;
import jakarta.persistence.Column;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ParkingDto {
    private Long parkingId;
    private String parkingName;
    private Integer parkingCapacity;
    private BigDecimal parkingPriceForHour;
    private BigDecimal parkingPriceForDay;
    private BigDecimal ParkingPriceForMonth;
    private Integer availableSpace;
    private ParkingStatus parkingStatus;
}
