package com.example.springbootpostgresqlcrud.customer.service;

import com.example.springbootpostgresqlcrud.customer.domain.CustomerEntity;
import com.example.springbootpostgresqlcrud.customer.model.request.CustomerUpdateRequest;
import com.example.springbootpostgresqlcrud.customer.model.response.CustomerResponse;
import com.example.springbootpostgresqlcrud.customer.model.response.CustomerWithOrderIdResponse;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface CustomerService {
  List<CustomerResponse> listCustomer();

  ResponseEntity<CustomerEntity> createCustomerWithOrder(CustomerEntity customerEntity);

  List<CustomerResponse> listCustomerWithoutOrder();

  ResponseEntity<String> deleteCustomer(Long id);

  ResponseEntity<CustomerResponse> updateCustomer(Long id, CustomerUpdateRequest customerUpdateRequest);

  List<CustomerWithOrderIdResponse> listCustomerWithOrderIdByQueryParam(String queryParam);
}
