package com.duvanlabrador.parking.DTO;

import com.duvanlabrador.parking.Entity.RoleEntity;
import com.duvanlabrador.parking.Util.Role;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    private Long userId;
    private String name;
    private String username;
    private String password;
    private String email;
    private String lastName;

}
