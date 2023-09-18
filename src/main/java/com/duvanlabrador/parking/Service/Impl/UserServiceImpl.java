package com.duvanlabrador.parking.Service.Impl;

import com.duvanlabrador.parking.DTO.UserResponse;
import org.springframework.data.domain.Pageable;

import com.duvanlabrador.parking.DTO.UserDto;
import com.duvanlabrador.parking.Entity.UserEntity;
import com.duvanlabrador.parking.Exception.Message;
import com.duvanlabrador.parking.Exception.GeneralException;
import com.duvanlabrador.parking.Repository.UserRepository;
import com.duvanlabrador.parking.Service.Interface.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.duvanlabrador.parking.Converter.UserConverter;


import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Slf4j
@Service
public class UserServiceImpl implements IUserService {
    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserResponse getAllUsers(int pageNumber, int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber,pageSize);
        Page<UserEntity> users = userRepository.findAll(pageable);

        List<UserEntity> userEntities = users.getContent();

        List<UserDto> content = userEntities.stream().map(user -> UserConverter.mapToDto(user)).collect(Collectors.toList());
        UserResponse response = new UserResponse();
        response.setContent(content);
        response.setPageNumber(users.getNumber());
        response.setPageSize(users.getSize());
        response.setTotalElements(users.getTotalElements());
        response.setTotalPages(users.getTotalPages());
        response.setLastPage(users.isLast());
        return response;
    }

    @Override
    public UserDto getUserById(Long userId) {
        UserEntity userEntity = this.userRepository.findById(userId).orElseThrow(() -> new GeneralException("User not found whit ID" + userId));
        return UserConverter.mapToDto(userEntity);
    }

    @Override
    public UserDto updateUser(Long userId, UserDto userDto) {
        UserEntity userEntity = this.userRepository.findById(userId).orElseThrow(() -> new GeneralException("User not 	found whit ID" + userId));

        userEntity.setName(userDto.getName());
        userEntity.setLastName(userDto.getLastName());
        userEntity.setUsername(userDto.getName());
        userEntity.setPassword(userDto.getPassword());
        userEntity.setEmail(userDto.getEmail());

        UserEntity updateUser = this.userRepository.save(userEntity);

        return UserConverter.mapToDto(updateUser);

    }

    @Override
    public void deleteUser(Long userId) {
        UserEntity user = this.userRepository.findById(userId).orElseThrow(() -> new GeneralException("user not found whit ID" + userId));

        this.userRepository.delete(user);
    }

    @Override
    public ResponseEntity<String> signUp(UserDto userDto) throws Exception {
        log.info("user register {}", userDto);
        UserEntity userEntity = this.userRepository.findByEmail(userDto.getEmail());
        if (Objects.isNull(userEntity)) {
            this.userRepository.save(
                        UserConverter.mapToEntity(userDto)
            );
            return Message.getResponseMessage("The user has been created correctly", HttpStatus.CREATED);
        }
            return Message.getResponseMessage("The user Already exist", HttpStatus.BAD_REQUEST);


    }
}


