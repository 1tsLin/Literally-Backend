package com.literally.backend.dtos;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
@Builder
public class CartItemDTO {

    private UUID userId;

    private ProductDTO product;

    private Integer quantity;
}
