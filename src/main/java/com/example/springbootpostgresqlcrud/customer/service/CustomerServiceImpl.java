package com.example.springbootpostgresqlcrud.customer.service;

import com.example.springbootpostgresqlcrud.core.mapstructmappers.CustomerMapper;
import com.example.springbootpostgresqlcrud.core.error.UserNotFoundException;
import com.example.springbootpostgresqlcrud.customer.domain.CustomerEntity;
import com.example.springbootpostgresqlcrud.customer.model.request.CustomerUpdateRequest;
import com.example.springbootpostgresqlcrud.customer.model.response.CustomerResponse;
import com.example.springbootpostgresqlcrud.customer.model.response.CustomerWithOrderIdResponse;
import com.example.springbootpostgresqlcrud.customer.repository.CustomerRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class CustomerServiceImpl implements CustomerService {
  private CustomerRepository customerRepository;
  private CustomerMapper customerMapper;

  @Override
  public List<CustomerResponse> listCustomer() {
    List<CustomerResponse> customerResponses = customerMapper.mapToCustomerResponse(customerRepository.findAll());
    return customerResponses;
//    List<CustomerResponse> customerListResponses = new ArrayList<>();
//    customerRepository.findAll().stream().forEach(customerEntity -> {
//      CustomerResponse customerListResponse = CustomerResponse.builder()
//          .name(customerEntity.getName())
//          .age(customerEntity.getAge())
//          .build();
//      customerListResponses.add(customerListResponse);
//    });
//    return customerListResponses;
  }

  @Override
  public ResponseEntity<CustomerEntity> createCustomerWithOrder(CustomerEntity customerEntity) {
    customerRepository.save(customerEntity);
    return ResponseEntity.status(HttpStatus.CREATED).build();
  }

  @Override
  public List<CustomerResponse> listCustomerWithoutOrder() {
    //witmapper
    List<CustomerResponse> customerResponses = customerMapper.mapToCustomerResponse(customerRepository.findCustomerWithoutOrder());
    return customerResponses;

    //without mappper
//    List<CustomerResponse> customerListResponses = new ArrayList<>();
//    customerRepository.findCustomerWithoutOrder().stream().forEach(customerEntity -> {
//      CustomerResponse customerListResponse = CustomerResponse.builder()
//          .name(customerEntity.getName())
//          .age(customerEntity.getAge())
//          .build();
//      customerListResponses.add(customerListResponse);
//    });
//    return customerListResponses;
  }

  @Override
  public ResponseEntity<String> deleteCustomer(Long id) {
    customerRepository.deleteById(id);
    return ResponseEntity.ok("Item with ID " + id + " has been deleted.");
  }

  @Override
  public ResponseEntity<CustomerResponse> updateCustomer(Long id, CustomerUpdateRequest customerUpdateRequest) {
    CustomerEntity customerEntity = customerRepository.findById(id).orElseThrow(() -> new UserNotFoundException("User Not Found"));

    customerEntity.setName((customerUpdateRequest.getName() != null ? customerUpdateRequest.getName() : customerEntity.getName()));
    customerEntity.setAge((customerUpdateRequest.getAge() != null ? customerUpdateRequest.getAge() : customerEntity.getAge()));

    CustomerEntity savedCustomerEntity = customerRepository.save(customerEntity);
    return ResponseEntity.ok(CustomerResponse.builder()
        .age(savedCustomerEntity.getAge())
        .name(savedCustomerEntity.getName())
        .build());
  }

  @Override
  public List<CustomerWithOrderIdResponse> listCustomerWithOrderIdByQueryParam(String queryParam) {
    List<Object[]> objectResults = customerRepository.findCustomerWithOrderIdByQueryParam(queryParam);
    List<CustomerWithOrderIdResponse> customerWithOrderIdResponses = new ArrayList<>();
    for (Object[] row : objectResults) {
      String name = (String) row[0];
      int age = (int) row[1];
      Long orderId = (Long) row[2];

      CustomerWithOrderIdResponse customerWithOrderIdResponse = CustomerWithOrderIdResponse.builder()
          .name(name)
          .age(age)
          .orderId(orderId)
          .build();
      customerWithOrderIdResponses.add(customerWithOrderIdResponse);
    }
    return customerWithOrderIdResponses;
  }
}
