package com.example.springbootpostgresqlcrud.core.error;

public class UserNotFoundException extends RuntimeException {
  public UserNotFoundException(String message){
    super(message);
  }

}
