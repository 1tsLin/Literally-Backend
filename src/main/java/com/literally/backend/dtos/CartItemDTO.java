package com.literally.backend.dtos;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@Builder
public class CartItemDTO {

    private UUID userId;

    private UUID productId;

    private Integer quantity;
}
