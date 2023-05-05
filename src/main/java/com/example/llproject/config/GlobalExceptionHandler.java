package com.example.llproject.config;

import com.example.llproject.config.ErrorResponse;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Locale;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

  private final MessageSource messageSource;

  public GlobalExceptionHandler(MessageSource messageSource) {
    this.messageSource = messageSource;
  }

  @ExceptionHandler(EntityNotFoundException.class)
  public ResponseEntity<ErrorResponse> handleEntityNotFound(EntityNotFoundException ex, WebRequest request, Locale locale) {
    ErrorResponse errorResponse = new ErrorResponse();
    errorResponse.setStatusCode(HttpStatus.NOT_FOUND);
    errorResponse.setErrorMessage(messageSource.getMessage("error.entityNotFound", null, locale));
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
  }

}
