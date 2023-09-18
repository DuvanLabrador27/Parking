package com.duvanlabrador.parking.Controller;


import com.duvanlabrador.parking.DTO.UserDto;
import com.duvanlabrador.parking.DTO.UserResponse;
import com.duvanlabrador.parking.Service.Interface.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class UserController {
    private final IUserService userService;
    @Autowired
    public UserController(IUserService userService){
        this.userService = userService;
    }

    @GetMapping("/users")
    public UserResponse getAllUsers(
            @RequestParam(value = "pageNo", defaultValue = "0", required = false) int pageNumber,
            @RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize){
        return this.userService.getAllUsers(pageNumber,pageSize);
    }

    @GetMapping("/users/{userId}")
    public ResponseEntity<UserDto> getUserById(@PathVariable Long userId){
        try {
            UserDto user = this.userService.getUserById(userId);
            return new ResponseEntity<>(user, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    @PostMapping("/signup")
    public ResponseEntity<String> createUser(@RequestBody UserDto userDto) throws Exception {

            return this.userService.signUp(userDto);

    }

    @PutMapping("/updateUser/{userId}")
    public ResponseEntity<UserDto> updateUser(@PathVariable Long userId, @RequestBody UserDto userDto){
        try {
            UserDto user = this.userService.updateUser(userId,userDto);
            return new ResponseEntity<>(user,HttpStatus.ACCEPTED);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }

    }

    @DeleteMapping("/deleteUser/{userId}")
    public ResponseEntity<String> deleteUser(@PathVariable Long userId){
        try {
            this.userService.deleteUser(userId);
            return new ResponseEntity<>("The user has been delete correctly", HttpStatus.ACCEPTED);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
