package com.erickhene.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;

@Slf4j
public class ObjectMapperUtil {
    private ObjectMapperUtil(){}
    private static ObjectMapper objectMapper;

    public static ObjectMapper generateObjectMapper(){
        if (objectMapper == null){
            objectMapper = new ObjectMapper();
        }
        return objectMapper;
    }

    public static Map<String, Object> generateHashMapFromObject(Object object){
        return objectMapper.convertValue(object, Map.class);
    }
}
