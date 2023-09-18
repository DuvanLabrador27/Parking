package com.duvanlabrador.parking.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class GeneralException extends RuntimeException{
    public GeneralException(String message){
        super(message);
    }
}
