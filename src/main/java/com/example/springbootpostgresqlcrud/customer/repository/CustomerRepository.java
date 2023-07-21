package com.example.springbootpostgresqlcrud.customer.repository;

import com.example.springbootpostgresqlcrud.customer.domain.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface CustomerRepository extends JpaRepository<CustomerEntity, Long> {
  Optional<CustomerEntity> findById(Long id);

  @Query(value = "SELECT c FROM CustomerEntity  c WHERE c.id NOT IN (SELECT DISTINCT customer FROM OrderEntity )")
  List<CustomerEntity> findCustomerWithoutOrder();

  @Query(value = "SELECT c.name,c.age,o.id FROM CustomerEntity c LEFT JOIN OrderEntity o ON o.customer.id=c.id where c.name ilike %:queryParam%")
  List<Object[]> findCustomerWithOrderIdByQueryParam(String queryParam);
}
