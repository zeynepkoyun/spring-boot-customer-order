package com.example.springbootpostgresqlcrud.order.model.request;

import com.example.springbootpostgresqlcrud.customer.domain.CustomerEntity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderCreateRequest {
  private double totalPrice;
  private CustomerEntity customer;
}
