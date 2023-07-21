package com.example.springbootpostgresqlcrud.core.mapstructmappers;

import com.example.springbootpostgresqlcrud.order.domain.OrderEntity;
import com.example.springbootpostgresqlcrud.order.model.response.OrderResponse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface OrderMapper {
  CustomerMapper INSTANCE = Mappers.getMapper(CustomerMapper.class);
  OrderResponse mapToOrderResponse(OrderEntity orderEntities);

}
