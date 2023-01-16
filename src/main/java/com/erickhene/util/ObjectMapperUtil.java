package com.erickhene.util;

import com.fasterxml.jackson.databind.ObjectMapper;

public class ObjectMapperUtil {
    private static ObjectMapper objectMapper;

    public static ObjectMapper generateObjectMapper(){
        if (objectMapper == null){
            objectMapper = new ObjectMapper();
        }
        return objectMapper;
    }
}
