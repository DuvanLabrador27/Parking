package com.duvanlabrador.parking.DTO;

import com.duvanlabrador.parking.Util.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    private String userStatus;
    private String role;

}
