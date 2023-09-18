package com.duvanlabrador.parking.Entity;

import com.duvanlabrador.parking.Util.ParkingStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "parking")
public class ParkingEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "parking_id")
    private Long parkingId;
    @Column(name = "parking_name", unique = true)
    private String parkingName;
    @Column(name = "parking_capacity")
    private Integer parkingCapacity;
    @Column(name = "parking_price_for_hour")
    private BigDecimal parkingPriceForHour;
    @Column(name = "parking_price_for_day")
    private BigDecimal parkingPriceForDay;
    @Column(name = "parking_price_for_month")
    private BigDecimal ParkingPriceForMonth;
    @Column(name = "available_space")
    private Integer availableSpace;
    @Column(name = "parking_status")
    @Enumerated
    private ParkingStatus parkingStatus;
    @ManyToOne(targetEntity = UserEntity.class)
    @JoinColumn(name = "user_id")
    private UserEntity user;

    @OneToMany(
            targetEntity = RegisterEntity.class,
            mappedBy = "parking",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY
    )
    private Set<RegisterEntity> register = new HashSet<>();


}
