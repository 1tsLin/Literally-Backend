package com.literally.backend.mappers;

import com.literally.backend.dtos.OrderItemDTO;
import com.literally.backend.entities.OrderItem;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class OrderItemMapper {

    private final OrderMapper orderMapper;
    private final ProductMapper productMapper;

    public OrderItemDTO mapToDto(OrderItem entity){
        return OrderItemDTO.builder()
                .order(orderMapper.mapToDto(entity.getOrder()))
                .product(productMapper.mapToDto(entity.getProduct()))
                .quantity(entity.getQuantity())
                .price(entity.getPrice())
                .build();
    }

    public OrderItem mapToEntity(OrderItemDTO dto) {
        return OrderItem.builder()
                .order(orderMapper.mapToEntity(dto.getOrder()))
                .product(productMapper.mapToEntity(dto.getProduct()))
                .quantity(dto.getQuantity())
                .price(dto.getPrice())
                .build();
    }
}
