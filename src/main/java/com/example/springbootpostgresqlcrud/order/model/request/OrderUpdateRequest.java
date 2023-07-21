package com.example.springbootpostgresqlcrud.order.model.request;

import lombok.Getter;

@Getter
public class OrderUpdateRequest {
  private double totalPrice;
}
