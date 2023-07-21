package com.example.springbootpostgresqlcrud.order.model.response;

import com.example.springbootpostgresqlcrud.customer.model.response.CustomerDTO;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
public class OrderResponse {
  private LocalDateTime createDate;
  private Double totalPrice;
  private CustomerDTO customer;
//  private String customerName;
//  private Integer customerAge;
}
