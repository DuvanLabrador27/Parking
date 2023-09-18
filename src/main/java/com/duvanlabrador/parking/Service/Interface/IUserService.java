package com.duvanlabrador.parking.Service.Interface;


import com.duvanlabrador.parking.DTO.UserDto;
import com.duvanlabrador.parking.DTO.UserResponse;
import org.springframework.http.ResponseEntity;



public interface IUserService {
    public UserResponse getAllUsers(int pageNumber, int pageSize);
    public UserDto getUserById(Long userId);

    public UserDto updateUser(Long userId, UserDto userDto);
    public void deleteUser(Long userId);

    ResponseEntity<String> signUp(UserDto userDto) throws Exception;
}
