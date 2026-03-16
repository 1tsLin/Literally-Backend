package com.literally.backend.mappers;

import com.literally.backend.dtos.OrderItemDTO;
import com.literally.backend.entities.OrderItem;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class OrderItemMapper {

    public OrderItemDTO mapToDto(OrderItem entity) {
        UUID orderId = entity.getOrder() != null ? entity.getOrder().getId() : null;
        UUID productId = entity.getProduct() != null ? entity.getProduct().getId() : null;

        return OrderItemDTO.builder()
                .orderId(orderId)
                .productId(productId)
                .quantity(entity.getQuantity())
                .price(entity.getPrice())
                .build();
    }
}
