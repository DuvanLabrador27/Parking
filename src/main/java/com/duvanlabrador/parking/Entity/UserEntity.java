package com.duvanlabrador.parking.Entity;

import com.duvanlabrador.parking.Util.Role;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
@Data
@DynamicInsert
@DynamicUpdate
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long userId;
    private String name;
    @Column(name = "last_name")
    private String lastName;
    private String username;
    private String password;
    @Email
    private String email;
    private String role;
    @Column(name = "user_status")
    private String userStatus;
    @OneToMany(
            targetEntity = ParkingEntity.class,
            mappedBy = "user",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY
    )
    private Set<ParkingEntity> parkingEntity = new HashSet<>();


}
