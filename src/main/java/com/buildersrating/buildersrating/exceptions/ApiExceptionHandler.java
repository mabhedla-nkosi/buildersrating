package com.buildersrating.buildersrating.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(ApiRequestException.class)
    public ResponseEntity<Object> handleApiRequestException(ApiExceptions e){
        HttpStatus badRequest=HttpStatus.BAD_REQUEST;
        ApiExceptions apiExceptions=new ApiExceptions(
                e.getHttpmessage(),
                "404",
                LocalDateTime.now()
        );
    //    return new ResponseEntity<>(apiExceptions,badRequest);
        return ResponseEntity.status(badRequest).body(apiExceptions);
    }
}
