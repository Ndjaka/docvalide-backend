package com.ghosttech.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ConvertData {
    public static String objectToString(Object object)  {
        String convertedData = null;
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            convertedData = objectMapper.writeValueAsString(object);
        }catch (JsonProcessingException e){
            e.printStackTrace();
        }

        return convertedData;
    }

    public  static Object stringToObject(String string, Class<?> objectClass){
        Object convertedData = null;
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            convertedData = objectMapper.readValue(string, objectClass);
        }catch (JsonProcessingException e){
            e.printStackTrace();
        }

        return convertedData;
    }
}
