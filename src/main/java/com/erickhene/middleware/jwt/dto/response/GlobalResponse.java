package com.erickhene.middleware.jwt.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GlobalResponse {
    public String message;
    public Integer code;
}
