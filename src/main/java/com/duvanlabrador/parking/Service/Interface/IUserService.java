package com.duvanlabrador.parking.Service.Interface;

import com.duvanlabrador.parking.DTO.RegisterDto;
import com.duvanlabrador.parking.DTO.UserDto;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;

public interface IUserService {
    public List<UserDto> getAllUsers();
    public UserDto getUserById(Long userId);
    public UserDto createUser(UserDto userDto);
    public UserDto updateUser(Long userId, UserDto userDto);
    public void deleteUser(Long userId);

    ResponseEntity<String> signUp(Map<String,String> requestMap);
}
