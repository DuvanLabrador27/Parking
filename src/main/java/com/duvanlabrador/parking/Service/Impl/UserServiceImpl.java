package com.duvanlabrador.parking.Service.Impl;

import com.duvanlabrador.parking.DTO.ParkingDto;
import com.duvanlabrador.parking.DTO.UserDto;
import com.duvanlabrador.parking.Entity.ParkingEntity;
import com.duvanlabrador.parking.Entity.UserEntity;
import com.duvanlabrador.parking.Exception.InvalidData;
import com.duvanlabrador.parking.Exception.Message;
import com.duvanlabrador.parking.Exception.NotFoundException;
import com.duvanlabrador.parking.Exception.SomethingWentWrong;
import com.duvanlabrador.parking.Repository.UserRepository;
import com.duvanlabrador.parking.Service.Interface.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@Slf4j
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

    @Override
    public ResponseEntity<String> signUp(Map<String, String> requestMap) {
        log.info("user register {}", requestMap);
        try{
            if(validateSignUp(requestMap)){
                UserEntity userEntity = this.userRepository.findByEmail(requestMap.get("email"));
                if(Objects.isNull(userEntity)){
                    this.userRepository.save(getUserMap(requestMap));
                    return Message.getResponseMessage("The user has been created correctly", HttpStatus.CREATED);
                }else {
                    return Message.getResponseMessage("The user Already exist", HttpStatus.BAD_REQUEST);
                }
            }else{
                return Message.getResponseMessage(InvalidData.message,HttpStatus.BAD_REQUEST);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return Message.getResponseMessage(SomethingWentWrong.message,HttpStatus.INTERNAL_SERVER_ERROR);
    }

    public boolean validateSignUp(Map<String, String> requestMap){
        if(requestMap.containsKey("name") && requestMap.containsKey("lastName") && requestMap.containsKey("email") && requestMap.containsKey("username") && requestMap.containsKey("password")){
            return true;
        }
        return false;
    }
    public UserEntity getUserMap(Map<String, String> requestMap){
        UserEntity userEntity = new UserEntity();
        userEntity.setName(requestMap.get("name"));
        userEntity.setLastName(requestMap.get("lastName"));
        userEntity.setEmail(requestMap.get("email"));
        userEntity.setUsername(requestMap.get("username"));
        userEntity.setPassword(requestMap.get("password"));
        userEntity.setUserStatus("false");
        userEntity.setRole("user");
        return userEntity;
    }


    public UserDto mapToDto(UserEntity userEntity){
        UserDto user = new UserDto();
        user.setUserId(userEntity.getUserId());
        user.setName(userEntity.getName());
        user.setLastName(userEntity.getLastName());
        user.setEmail(userEntity.getEmail());
        user.setRole(userEntity.getRole());
        user.setUserStatus(userEntity.getUserStatus());
        return user;
    }

    public UserEntity mapToEntity(UserDto userDto){
        UserEntity user = new UserEntity();

        user.setName(userDto.getName());
        user.setLastName(userDto.getLastName());
        user.setEmail(userDto.getEmail());
        user.setRole(userDto.getRole());
        user.setUserStatus(userDto.getUserStatus());
        return user;
    }
}
