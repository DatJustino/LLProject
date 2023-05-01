package com.example.llproject.controller;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@ControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(EntityNotFoundException.class)
  public ResponseEntity<ErrorResponse> handleEntityNotFound(EntityNotFoundException ex) {
    ErrorResponse errorResponse = new ErrorResponse() {
      @Override
      public HttpStatusCode getStatusCode() {
        return null;
      }

      @Override
      public ProblemDetail getBody() {
        return null;
      }
    };
    return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
  }


}
