package com.example.springbootpostgresqlcrud.order.service;

import com.example.springbootpostgresqlcrud.core.error.OrderNotFoundException;
import com.example.springbootpostgresqlcrud.core.error.UserNotFoundException;
import com.example.springbootpostgresqlcrud.core.mapstructmappers.OrderMapper;
import com.example.springbootpostgresqlcrud.customer.domain.CustomerEntity;
import com.example.springbootpostgresqlcrud.customer.repository.CustomerRepository;
import com.example.springbootpostgresqlcrud.order.domain.OrderEntity;
import com.example.springbootpostgresqlcrud.order.model.request.OrderCreateRequest;
import com.example.springbootpostgresqlcrud.order.model.request.OrderUpdateRequest;
import com.example.springbootpostgresqlcrud.order.model.response.OrderResponse;
import com.example.springbootpostgresqlcrud.order.repository.OrderRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class OrderServiceImpl implements OrderService {
  private OrderRepository orderRepository;
  private CustomerRepository customerRepository;
  private OrderMapper orderMapper;

  @Override
  public List<OrderResponse> listAllOrder() {
    //mapper with
    List<OrderEntity> orderEntities = orderRepository.findAll();
    return orderEntities.stream().map(orderMapper::mapToOrderResponse)
        .collect(Collectors.toList());

//    mapper without
//    List<OrderResponse> orderListResponses = new ArrayList<>();
//    orderRepository.findAll().stream().forEach(orderEntity -> {
//      CustomerEntity customerInfo = orderEntity.getCustomer();
//      OrderResponse orderListResponse = OrderResponse.builder()
//          .createDate(orderEntity.getCreateDate())
//          .totalPrice(orderEntity.getTotalPrice())
//          .customer(CustomerDTO.builder()
//              .age(customerInfo.getAge())
//              .name(customerInfo.getName())
//              .build())
//          .build();
//      orderListResponses.add(orderListResponse);
//    });
//    return orderListResponses;
  }

  @Override
  public List<OrderResponse> listOrdersByDateGreaterThan(LocalDateTime createDate) {
    //mapper with
    List<OrderEntity> orderEntities = orderRepository.findOrdersByDateGreaterThan(createDate);
    return orderEntities.stream().map(orderMapper::mapToOrderResponse)
        .collect(Collectors.toList());

    // mapper without
//    List<OrderResponse> orderListResponses = new ArrayList<>();
//    orderRepository.findOrdersByDateGreaterThan(createDate).stream().forEach(orderEntity -> {
//      CustomerEntity customerInfo = orderEntity.getCustomer();
//      OrderResponse orderListResponse = OrderResponse.builder()
//          .createDate(orderEntity.getCreateDate())
//          .totalPrice(orderEntity.getTotalPrice())
//          .customerName(customerInfo.getName())
//          .customerAge(customerInfo.getAge())
//          .build();
//      orderListResponses.add(orderListResponse);
//    });
//    return orderListResponses;
  }

  @Override
  @Transactional
  public ResponseEntity<OrderEntity> createOrder(OrderCreateRequest orderCreateRequest) {
    CustomerEntity customerEntity = customerRepository.findById(orderCreateRequest.getCustomer().getId()).orElseThrow(() -> new UserNotFoundException("User Not Found"));
    OrderEntity orderEntity = OrderEntity.builder()
        .totalPrice(orderCreateRequest.getTotalPrice())
        .customer(customerEntity)
        .build();
    orderRepository.save(orderEntity);
    return ResponseEntity.status(HttpStatus.CREATED).build();
  }

  @Override
  public ResponseEntity<String> deleteOrder(Long id) {
    orderRepository.deleteById(id);
    return ResponseEntity.ok("Item with ID " + id + " has been deleted.");
  }

  @Override
  public ResponseEntity<OrderResponse> updateOrder(Long id, OrderUpdateRequest orderUpdateRequest) {
    OrderEntity orderEntity = orderRepository.findById(id).orElseThrow(() -> new OrderNotFoundException("Order number not found"));
    orderEntity.setTotalPrice(orderUpdateRequest.getTotalPrice());
    OrderEntity savedOrderEntity = orderRepository.save(orderEntity);

//    //mapper without
//    return ResponseEntity.ok(OrderResponse.builder()
//        .createDate(savedOrderEntity.getCreateDate())
//        .totalPrice(savedOrderEntity.getTotalPrice())
//        .customer(CustomerDTO.builder()
//            .age(savedOrderEntity.getCustomer().getAge())
//            .name(savedOrderEntity.getCustomer().getName())
//            .build())
//        .build());

    //mapper with
    return ResponseEntity.ok(orderMapper.mapToOrderResponse(savedOrderEntity));
  }
}
