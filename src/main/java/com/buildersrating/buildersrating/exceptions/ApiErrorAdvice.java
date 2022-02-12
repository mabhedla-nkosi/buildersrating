package com.buildersrating.buildersrating.exceptions;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;

@RestControllerAdvice
@ControllerAdvice
@EnableWebMvc
public class ApiErrorAdvice extends ResponseEntityExceptionHandler {
    /*@ExceptionHandler(IllegalAccessException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiExceptions handleIllegalAccessException(IllegalAccessException exception, HttpServletRequest request){
        ApiExceptions apiExceptions=new ApiExceptions(exception.getMessage(),"400", LocalDateTime.now(),request.getServletPath());
        return apiExceptions;
    }*/

    @ExceptionHandler(ApiRequestException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiExceptions handleApiRequestException(ApiRequestException exception, HttpServletRequest request){
        ApiExceptions apiExceptions=new ApiExceptions(exception.getMessage(),HttpStatus.BAD_REQUEST, LocalDateTime.now(),request.getServletPath());
        return apiExceptions;
    }

    @Override
    protected ResponseEntity<Object> handleNoHandlerFoundException(NoHandlerFoundException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        ApiExceptions apiExceptions=new ApiExceptions(ex.getMessage(),HttpStatus.NOT_FOUND, LocalDateTime.now());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apiExceptions);
    //    return new ResponseEntity<Object>(new ApiExceptions(ex.getMessage(),"404", LocalDateTime.now(),request.getContextPath()),HttpStatus.NOT_FOUND);
    }
}
