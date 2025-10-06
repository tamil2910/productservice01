package com.example.productservice01.controller_advice;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.productservice01.dtos.ErrorResponseDto;
import com.example.productservice01.exception.ProductNotFoundException;

@RestControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(ProductNotFoundException.class)
  public ResponseEntity<ErrorResponseDto> handleProductNotFound(ProductNotFoundException e) {
    ErrorResponseDto error = new ErrorResponseDto();
    error.setMessage(e.getMessage());
    error.setSolution("Try with different id");
    error.setStatus_code(HttpStatus.NOT_FOUND.value());
    error.setTimestamp(LocalDateTime.now());
    
    ResponseEntity response = new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    return response;
  }
}
