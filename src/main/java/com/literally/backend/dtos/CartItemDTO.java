package com.literally.backend.dtos;

import lombok.*;

import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CartItemDTO {

    private UUID userId;

    private UUID productId;

    private Integer quantity;
}
