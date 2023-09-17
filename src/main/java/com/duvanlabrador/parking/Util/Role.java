package com.duvanlabrador.parking.Util;

import com.duvanlabrador.parking.Exception.GeneralException;
import lombok.AllArgsConstructor;


import java.util.Objects;


@AllArgsConstructor
public enum Role {
    ADMIN("ADMIN"), BUSINESS_PARTNER("BUSINESS PARTNER");
    private final String role;
    public static void getRoleValid(String role) throws Exception {
        for (Role value : values()) {
            if(Objects.equals(role,value.role)){
               return;
            }
        }
        throw new GeneralException("Role not found");
    }



}
