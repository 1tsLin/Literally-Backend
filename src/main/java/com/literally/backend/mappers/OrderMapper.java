package com.literally.backend.mappers;

import com.literally.backend.dtos.OrderDTO;
import com.literally.backend.entities.Order;
import com.literally.backend.entities.OrderItem;
import com.literally.backend.entities.User;
import com.literally.backend.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class OrderMapper {

    private final UserService userService;

    public OrderDTO mapToDto(Order entity) {
        UUID userId = entity.getUser() != null ? entity.getUser().getId() : null;

        return OrderDTO.builder()
                .id(entity.getId())
                .status(entity.getStatus())
                .userId(userId)
                .creationDate(entity.getCreationDate())
                .updateDate(entity.getUpdateDate())
                .itemIds(entity.getItems().stream()
                        .map(OrderItem::getId)
                        .collect(Collectors.toSet()))
                .build();
    }

    public Order mapToEntity(OrderDTO dto) {
        User user = dto.getUserId() != null ? userService.getUserById(dto.getUserId()) : null;

        return Order.builder()
                .status(dto.getStatus())
                .user(user)
                .creationDate(dto.getCreationDate())
                .updateDate(dto.getUpdateDate())
                .build();
    }
}
