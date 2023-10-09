package com.system.contact.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Date;

@ControllerAdvice
public class ControllerExceptionHandler {

  @ExceptionHandler(CustomException.class)
  public static ResponseEntity<ErrorMessage> resourceNotFoundException(CustomException ex) {
    ErrorMessage message = new ErrorMessage(
            ex.getErrorCode(),
        new Date(),
        ex.getMessage(),
        ex.getEnMessage());
    return new ResponseEntity<>(message, HttpStatus.INTERNAL_SERVER_ERROR);
  }

}
