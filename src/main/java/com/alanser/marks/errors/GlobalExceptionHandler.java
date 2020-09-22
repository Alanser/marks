package com.alanser.marks.errors;

import java.io.IOException;
import javax.persistence.EntityNotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

  @ExceptionHandler(EntityNotFoundException.class)
  public ResponseEntity<String> handleEntityNotFoundException(EntityNotFoundException ex) {
    return new ResponseEntity<String>(ex.getMessage(), HttpStatus.NOT_FOUND);
  }

  @ExceptionHandler(IOException.class)
  public ResponseEntity<String> handleIOException(IOException ex) {
    return new ResponseEntity<String>("Ошибка чтения файла", HttpStatus.BAD_REQUEST);
  }

  @Override
  public ResponseEntity<Object> handleHttpMediaTypeNotSupported(HttpMediaTypeNotSupportedException ex,
      HttpHeaders headers, HttpStatus status, WebRequest request) {
    return new ResponseEntity<>("Media Type not supported: " + ex.getMessage(), HttpStatus.UNSUPPORTED_MEDIA_TYPE);
  }

}
