package com.literally.backend.mappers;

import com.literally.backend.dtos.OrderDTO;
import com.literally.backend.entities.Order;
import com.literally.backend.entities.User;
import com.literally.backend.services.UserService;
import lombok.RequiredArgsConstructor;

import java.util.UUID;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class OrderMapper {

    private final OrderItemMapper orderItemMapper;
    private final UserService userService;

    public OrderDTO mapToDto(Order entity){
        UUID userId = entity.getUser() != null ? entity.getUser().getId() : null;

        return OrderDTO.builder()
                .id(entity.getId())
                .status(entity.getStatus())
                .userId(userId)
                .creationDate(entity.getCreationDate())
                .updateDate(entity.getUpdateDate())
                .items(entity.getItems().stream()
                        .map(orderItemMapper::mapToDto)
                        .collect(Collectors.toSet()))
                .build();
    }

    public Order mapToEntity(OrderDTO dto){
        User user = dto.getUserId() != null ? userService.getById(dto.getUserId()) : null;

        return Order.builder()
                .id(dto.getId())
                .status(dto.getStatus())
                .user(user)
                .creationDate(dto.getCreationDate())
                .updateDate(dto.getUpdateDate())
                .items(dto.getItems().stream()
                        .map(orderItemMapper::mapToEntity)
                        .collect(Collectors.toSet()))
                .build();
    }
}
