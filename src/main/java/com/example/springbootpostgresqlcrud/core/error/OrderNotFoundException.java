package com.example.springbootpostgresqlcrud.core.error;

public class OrderNotFoundException extends RuntimeException {
  public OrderNotFoundException(String message) {
    super(message);
  }
}
