package com.example.springbootpostgresqlcrud.customer.model.request;

import lombok.Getter;

@Getter
public class CustomerUpdateRequest {
  private String name;
  private Integer age;
}
