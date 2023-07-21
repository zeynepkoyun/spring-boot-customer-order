package com.example.springbootpostgresqlcrud.customer.model.request;

import com.example.springbootpostgresqlcrud.customer.domain.CustomerEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CustomerWithOrdersAddRequest {
  CustomerEntity customers;
}
