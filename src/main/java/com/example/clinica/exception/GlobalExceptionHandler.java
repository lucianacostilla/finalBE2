package com.example.clinica.exception;

import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobalExceptionHandler {
    private static final Logger logger = Logger.getLogger(GlobalExceptionHandler.class);
    @ExceptionHandler({ResourceNotFoundException.class})
    public ResponseEntity<String> todosLosErrores(ResourceNotFoundException rnfe, WebRequest request){
        logger.error(rnfe.getMessage());
        return new ResponseEntity("Error: " + rnfe.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);

    }
}
