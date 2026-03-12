package com.literally.backend.dtos;

import lombok.*;

@Getter
@Setter
@Builder
public class CartItemDTO {

    private ProductDTO product;

    private Integer quantity;
}
