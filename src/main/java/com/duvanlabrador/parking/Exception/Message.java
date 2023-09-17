package com.duvanlabrador.parking.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class Message {
    private Message(){

    }
    public static ResponseEntity<String> getResponseMessage(String messageTemp, HttpStatus httpStatus){
        return new ResponseEntity<>("message: " +  messageTemp, httpStatus);
    }
}
