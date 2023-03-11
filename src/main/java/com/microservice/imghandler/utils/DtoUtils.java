package com.microservice.imghandler.utils;

import java.lang.reflect.Field;

import com.microservice.imghandler.dtos.HandleImageDTO;

public class DtoUtils {

    public static boolean hasNullFields(HandleImageDTO data) throws IllegalAccessException {
        for (Field field : data.getClass().getDeclaredFields()) {
            field.setAccessible(true);
            if (field.get(data) == null) {
                return true;
            }
        }
        return false;
    }
}
