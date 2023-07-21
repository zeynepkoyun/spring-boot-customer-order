package com.example.springbootpostgresqlcrud.customer.model.response;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class CustomerDTO {
  private String name;
  private Integer age;

}
