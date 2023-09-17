package com.duvanlabrador.parking.Converter;

import com.duvanlabrador.parking.DTO.UserDto;
import com.duvanlabrador.parking.Entity.UserEntity;
import com.duvanlabrador.parking.Util.Role;

public final class UserConverter {

    public static UserDto mapToDto(UserEntity userEntity){
        UserDto user = new UserDto();
        user.setUserId(userEntity.getUserId());
        user.setName(userEntity.getName());
        user.setLastName(userEntity.getLastName());
        user.setEmail(userEntity.getEmail());
        user.setRole(userEntity.getRole());
        user.setUserStatus(userEntity.getUserStatus());
        return user;
    }

    public static UserEntity mapToEntity(UserDto userDto) throws Exception {
        Role.getRoleValid(userDto.getRole());
        UserEntity user = new UserEntity();

        user.setUsername(userDto.getUsername());
        user.setPassword(userDto.getPassword());
        user.setName(userDto.getName());
        user.setLastName(userDto.getLastName());
        user.setEmail(userDto.getEmail());
        user.setRole(userDto.getRole());
        user.setUserStatus(userDto.getUserStatus());
        return user;
    }
}
