package com.example.springbootpostgresqlcrud.order.controller;

import com.example.springbootpostgresqlcrud.order.domain.OrderEntity;
import com.example.springbootpostgresqlcrud.order.model.request.OrderCreateRequest;
import com.example.springbootpostgresqlcrud.order.model.request.OrderUpdateRequest;
import com.example.springbootpostgresqlcrud.order.model.response.OrderResponse;
import com.example.springbootpostgresqlcrud.order.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {
  private final OrderService orderService;

  @PostMapping
  public ResponseEntity<OrderEntity> createCustomerWithOrders(@RequestBody OrderCreateRequest orderCreateRequest) {
    return orderService.createOrder(orderCreateRequest);
  }

  @GetMapping
  public List<OrderResponse> listAllOrder() {
    return orderService.listAllOrder();
  }

  @GetMapping("/date")
  public List<OrderResponse> getOrderByDateParam(@RequestParam("createDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime createDate) {
    return orderService.listOrdersByDateGreaterThan(createDate);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<String> deleteCustomer(@PathVariable Long id) {
    return orderService.deleteOrder(id);
  }

  @PutMapping("/{id}")
  public ResponseEntity<OrderResponse> updateCustomer(@PathVariable Long id, @RequestBody OrderUpdateRequest orderUpdateRequest) {
    return orderService.updateOrder(id, orderUpdateRequest);
  }

}
