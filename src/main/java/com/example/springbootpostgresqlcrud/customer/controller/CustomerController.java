package com.example.springbootpostgresqlcrud.customer.controller;

import com.example.springbootpostgresqlcrud.customer.domain.CustomerEntity;
import com.example.springbootpostgresqlcrud.customer.model.request.CustomerUpdateRequest;
import com.example.springbootpostgresqlcrud.customer.model.response.CustomerResponse;
import com.example.springbootpostgresqlcrud.customer.model.response.CustomerWithOrderIdResponse;
import com.example.springbootpostgresqlcrud.customer.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customers")
@RequiredArgsConstructor
public class CustomerController {
  private final CustomerService customerService;

  @PostMapping()
  public ResponseEntity<CustomerEntity> createCustomerWithOrders(@RequestBody CustomerEntity customerEntity) {
    return customerService.createCustomerWithOrder(customerEntity);
  }

  @GetMapping()
  public List<CustomerResponse> listAllCustomer() {
    return customerService.listCustomer();
  }

  @GetMapping("/without-order")
  public List<CustomerResponse> listCustomerWithoutOrder() {
    return customerService.listCustomerWithoutOrder();
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<String> deleteCustomer(@PathVariable Long id) {
    return customerService.deleteCustomer(id);
  }

  @PutMapping("/{id}")
  public ResponseEntity<CustomerResponse> updateCustomer(@PathVariable Long id, @RequestBody CustomerUpdateRequest customerUpdateRequest) {
    return customerService.updateCustomer(id, customerUpdateRequest);
  }

  @GetMapping("/info")
  public ResponseEntity<List<CustomerWithOrderIdResponse>> listCustomerWithOrderByQueryParam(@RequestParam("query") String queryParam) {
    return ResponseEntity.ok(customerService.listCustomerWithOrderIdByQueryParam(queryParam));
  }

}
