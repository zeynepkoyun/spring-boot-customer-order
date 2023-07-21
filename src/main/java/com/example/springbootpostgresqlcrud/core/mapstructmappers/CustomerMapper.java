package com.example.springbootpostgresqlcrud.core.mapstructmappers;

import com.example.springbootpostgresqlcrud.customer.domain.CustomerEntity;
import com.example.springbootpostgresqlcrud.customer.model.response.CustomerResponse;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CustomerMapper {
//  CustomerMapper INSTANCE = Mappers.getMapper(CustomerMapper.class);
  List<CustomerResponse> mapToCustomerResponse(List<CustomerEntity> customerEntity);
}
