package com.example.springbootpostgresqlcrud.order.service;

import com.example.springbootpostgresqlcrud.customer.model.request.CustomerUpdateRequest;
import com.example.springbootpostgresqlcrud.customer.model.response.CustomerResponse;
import com.example.springbootpostgresqlcrud.order.domain.OrderEntity;
import com.example.springbootpostgresqlcrud.order.model.request.OrderCreateRequest;
import com.example.springbootpostgresqlcrud.order.model.request.OrderUpdateRequest;
import com.example.springbootpostgresqlcrud.order.model.response.OrderResponse;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.util.List;

public interface OrderService {
  List<OrderResponse> listAllOrder();

  List<OrderResponse> listOrdersByDateGreaterThan(LocalDateTime createDate);

  ResponseEntity<OrderEntity> createOrder(OrderCreateRequest orderCreateRequest);

  ResponseEntity<String> deleteOrder(Long id);

  ResponseEntity<OrderResponse> updateOrder(Long id, OrderUpdateRequest orderUpdateRequest);
}
