package com.example.productservice01.exception;

public class ProductNotFoundException extends RuntimeException{
  public ProductNotFoundException(String message) {
    super(message);
  }
}
