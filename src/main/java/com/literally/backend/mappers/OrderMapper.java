package com.literally.backend.mappers;

import com.literally.backend.dtos.OrderDTO;
import com.literally.backend.entities.Order;
import lombok.RequiredArgsConstructor;

import java.util.stream.Collectors;

@RequiredArgsConstructor
public class OrderMapper {

    private final UserMapper userMapper;
    private final OrderItemMapper orderItemMapper;

    public OrderDTO mapToDto(Order entity){
        return OrderDTO.builder()
                .id(entity.getId())
                .status(entity.getStatus())
                .user(userMapper.mapToDto(entity.getUser()))
                .creationDate(entity.getCreationDate())
                .updateDate(entity.getUpdateDate())
                .items(entity.getItems().stream()
                        .map(orderItemMapper::mapToDto)
                        .collect(Collectors.toSet()))
                .build();
    }

    public Order mapToEntity(OrderDTO dto){
        return Order.builder()
                .id(dto.getId())
                .status(dto.getStatus())
                .user(userMapper.mapToEntity(dto.getUser()))
                .creationDate(dto.getCreationDate())
                .updateDate(dto.getUpdateDate())
                .items(dto.getItems().stream()
                        .map(orderItemMapper::mapToEntity)
                        .collect(Collectors.toSet()))
                .build();
    }
}
