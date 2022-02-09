package com.buildersrating.buildersrating.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;

@RestControllerAdvice
public class ApiErrorAdvice {
    /*@ExceptionHandler(IllegalAccessException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiExceptions handleIllegalAccessException(IllegalAccessException exception, HttpServletRequest request){
        ApiExceptions apiExceptions=new ApiExceptions(exception.getMessage(),"400", LocalDateTime.now(),request.getServletPath());
        return apiExceptions;
    }*/

    @ExceptionHandler(ApiRequestException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiExceptions handleApiRequestException(ApiRequestException exception, HttpServletRequest request){
        ApiExceptions apiExceptions=new ApiExceptions(exception.getMessage(),"400", LocalDateTime.now(),request.getServletPath());
        return apiExceptions;
    }

}
