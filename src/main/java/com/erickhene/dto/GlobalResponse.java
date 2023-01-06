package com.erickhene.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GlobalResponse<T> {
    public String message;
    public Integer code;
    public T data;

    public GlobalResponse(String message, Integer code){
        setMessage(message);
        setCode(code);
    }
}
