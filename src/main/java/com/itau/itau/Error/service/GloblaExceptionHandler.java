package com.itau.itau.Error.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.itau.itau.service.TransactionsService;

@RestControllerAdvice
public class GloblaExceptionHandler {
    private static final Logger logger = LoggerFactory.getLogger(GloblaExceptionHandler.class);

    
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Void> handlerExeptionUnprocessableEntity(IllegalArgumentException ex){
        logger.error("Illegal argument error", ex.getMessage());
        return new ResponseEntity<Void>(HttpStatus.UNPROCESSABLE_ENTITY); 
    }

    // @ExceptionHandler(Exception.class)
    // public ResponseEntity<Void> handlerExeption(Exception ex){
    //     logger.error("Internal server error", ex.getMessage());
    //     return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST); 
    // }
}
