package com.example.springbootpostgresqlcrud.order.domain;

import com.example.springbootpostgresqlcrud.customer.domain.CustomerEntity;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "orders")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", nullable = false)
  private Long id;

  @Column(name = "create_date")
  @CreationTimestamp
  private LocalDateTime createDate;

  @Column(name = "total_price")
  private Double totalPrice;

  @ManyToOne()
  @JoinColumn(name = "customer_id")
  private CustomerEntity customer;
}
