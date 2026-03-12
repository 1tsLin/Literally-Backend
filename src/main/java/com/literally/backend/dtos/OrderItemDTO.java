package com.literally.backend.dtos;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
public class OrderItemDTO {
    private OrderDTO order;
    private ProductDTO product;
    private Integer quantity;
    private BigDecimal price;
}
