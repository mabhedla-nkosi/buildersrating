package com.buildersrating.buildersrating.exceptions;

import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

public class ApiExceptions {

    private String httpmessage;
    private HttpStatus httpStatus;
    private LocalDateTime timestamp;
    private String path;

    public ApiExceptions() {
    }

    public ApiExceptions(String httpmessage, HttpStatus httpStatus, LocalDateTime timestamp, String path) {
        this.httpmessage = httpmessage;
        this.httpStatus = httpStatus;
        this.timestamp = timestamp;
        this.path = path;
    }

    public ApiExceptions(String httpmessage, HttpStatus httpStatus, LocalDateTime timestamp) {
        this.httpmessage = httpmessage;
        this.httpStatus = httpStatus;
        this.timestamp = timestamp;
      //  this.path = path;
    }

    public String getHttpmessage() {
        return httpmessage;
    }

    public void setHttpmessage(String httpmessage) {
        this.httpmessage = httpmessage;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public void setHttpStatus(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
