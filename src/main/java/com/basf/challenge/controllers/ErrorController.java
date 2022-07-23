package com.basf.challenge.controllers;

import com.basf.challenge.exceptions.NotAuthorizedException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.NoSuchElementException;

@RestControllerAdvice
@Slf4j
public class ErrorController {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NoSuchElementException.class)
    public String entityNotFound() {
        log.warn("Entity not found");
        return "Not found";
    }

    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(NotAuthorizedException.class)
    public String handleNotAuthorized() {
        log.warn("Not authorized");
        return "Not authorized";
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public String handleInternalError(Exception e) {
        log.error("Unhandled Exception in Controller", e);
        return "Internal error";
    }

}
