package com.itau.itau.Error.service;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GloblaExceptionHandler {
    
    
    
    
    
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Void> handlerExeption(Exception ex){
        return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST); 
    }

}
