package com.example.springbootpostgresqlcrud.customer.domain;

import com.example.springbootpostgresqlcrud.order.domain.OrderEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "customers")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CustomerEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", nullable = false)
  private Long id;

  @Column(name = "name")
  private String name;

  @Column(name = "age")
  private Integer age;

  @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
  List<OrderEntity> orders;

}
