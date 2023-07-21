package com.example.springbootpostgresqlcrud.order.repository;

import com.example.springbootpostgresqlcrud.order.domain.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface OrderRepository extends JpaRepository<OrderEntity, Long> {

  @Query(value = "SELECT o FROM OrderEntity o  where o.createDate > :createDate")
  List<OrderEntity> findOrdersByDateGreaterThan(LocalDateTime createDate);

  Optional<OrderEntity> findById(Long id);
}
