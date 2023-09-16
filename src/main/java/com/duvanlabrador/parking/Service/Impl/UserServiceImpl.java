package com.duvanlabrador.parking.Service.Impl;

import com.duvanlabrador.parking.DTO.ParkingDto;
import com.duvanlabrador.parking.DTO.UserDto;
import com.duvanlabrador.parking.Entity.ParkingEntity;
import com.duvanlabrador.parking.Entity.UserEntity;
import com.duvanlabrador.parking.Exception.NotFoundException;
import com.duvanlabrador.parking.Repository.UserRepository;
import com.duvanlabrador.parking.Service.Interface.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements IUserService {
    private final UserRepository userRepository;
    @Autowired
    public UserServiceImpl(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @Override
    public List<UserDto> getAllUsers() {
        List<UserEntity> userEntity = this.userRepository.findAll();
        return userEntity.stream().map(user -> mapToDto(user)).collect(Collectors.toList());
    }

    @Override
    public UserDto getUserById(Long userId) {
        UserEntity userEntity = this.userRepository.findById(userId).orElseThrow(() -> 	new NotFoundException("User not found whit ID" + userId));
        return mapToDto(userEntity);
    }

    @Override
    public UserDto createUser(UserDto userDto) {
        UserEntity userEntity = mapToEntity(userDto);
        UserEntity newUser = this.userRepository.save(userEntity);
        UserDto user = mapToDto(newUser);
        return user;
    }

    @Override
    public UserDto updateUser(Long userId, UserDto userDto) {
        UserEntity userEntity = this.userRepository.findById(userId).orElseThrow(() -> new NotFoundException	("User not 	found whit ID" + userId));

        userEntity.setName(userDto.getName());
        userEntity.setLastName(userDto.getLastName());
        userEntity.setUsername(userDto.getName());
        userEntity.setPassword(userDto.getPassword());
        userEntity.setEmail(userDto.getEmail());

        UserEntity updateUser = this.userRepository.save(userEntity);

        return mapToDto(updateUser);

    }

    @Override
    public void deleteUser(Long userId) {
        UserEntity user = this.userRepository.findById(userId).orElseThrow(() -> new NotFoundException	("user not found whit ID" + userId));

        this.userRepository.delete(user);
    }


    public UserDto mapToDto(UserEntity userEntity){
        UserDto user = new UserDto();
        user.setUserId(userEntity.getUserId());
        user.setName(userEntity.getName());
        user.setLastName(userEntity.getLastName());
        user.setEmail(userEntity.getEmail());
        return user;
    }

    public UserEntity mapToEntity(UserDto userDto){
        UserEntity user = new UserEntity();

        user.setName(userDto.getName());
        user.setLastName(userDto.getLastName());
        user.setEmail(userDto.getEmail());
        return user;
    }
}
