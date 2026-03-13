package com.literally.backend.dtos;

import lombok.*;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter
@Builder
public class OrderItemDTO {
    private UUID orderId;
    private UUID productId;
    private Integer quantity;
    private BigDecimal price;
}
