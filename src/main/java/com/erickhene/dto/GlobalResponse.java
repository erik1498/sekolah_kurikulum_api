package com.erickhene.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class GlobalResponse<T> {
    public String message;
    public Integer code;
    public T data;
}
