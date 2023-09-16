package com.duvanlabrador.parking.Controller;

import com.duvanlabrador.parking.Exception.Message;
import com.duvanlabrador.parking.Exception.SomethingWentWrong;
import com.duvanlabrador.parking.Service.Interface.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {
    private final IUserService userService;
    @Autowired
    public UserController(IUserService userService){
        this.userService = userService;
    }
    @PostMapping("/signup")
    public ResponseEntity<String> createUser(@RequestBody(required = true) Map<String,String> requestMap){
        try {
            return this.userService.signUp(requestMap);
        }catch (Exception e){
            e.printStackTrace();
        }
        return Message.getResponseMessage(SomethingWentWrong.message, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
