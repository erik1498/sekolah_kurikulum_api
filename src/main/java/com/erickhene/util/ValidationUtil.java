package com.erickhene.util;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;

import com.erickhene.dto.GlobalResponse;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ValidationUtil {
    public static ResponseEntity<GlobalResponse<?>> generateError(Errors errors){
        String errorMessage = "";
        for (ObjectError error : errors.getAllErrors()) {
            log.info("error = {}", error.getDefaultMessage());
            errorMessage = errorMessage.concat(error.getDefaultMessage()).concat(",");
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST.value()).body(
            new GlobalResponse<>(errorMessage.substring(0, errorMessage.length() - 1), HttpStatus.BAD_REQUEST.value())
        );
    }
}
