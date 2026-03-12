package com.literally.backend.mappers;

import com.literally.backend.dtos.CartItemDTO;
import com.literally.backend.entities.CartItem;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CartItemMapper {

    private final ProductMapper productMapper;

    public CartItemDTO mapToDto(CartItem entity){
        return CartItemDTO.builder()
                .product(productMapper.mapToDto(entity.getProduct()))
                .quantity(entity.getQuantity())
                .build();
    }

    public CartItem mapToEntity(CartItemDTO dto){
        return CartItem.builder()
                .product(productMapper.mapToEntity(dto.getProduct()))
                .quantity(dto.getQuantity())
                .build();
    }
}
