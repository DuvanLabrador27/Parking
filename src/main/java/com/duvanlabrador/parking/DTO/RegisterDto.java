package com.duvanlabrador.parking.DTO;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegisterDto {
    private Long registerId;
    private LocalDateTime entryTime;
    private LocalDateTime departureTime;

}
