package com.duvanlabrador.parking.Service.Interface;

import com.duvanlabrador.parking.DTO.RegisterDto;
import com.duvanlabrador.parking.DTO.UserDto;

import java.util.List;

public interface IUserService {
    public List<UserDto> getAllUsers();
    public UserDto getUserById(Long userId);
    public UserDto createUser(UserDto userDto);
    public UserDto updateUser(Long userId, UserDto userDto);
    public void deleteUser(Long userId);
}
