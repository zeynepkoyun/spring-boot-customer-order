package com.example.springbootpostgresqlcrud.customer.model.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class CustomerWithOrderIdResponse {
  private String name;
  private Integer age;
  private Long orderId;
}
