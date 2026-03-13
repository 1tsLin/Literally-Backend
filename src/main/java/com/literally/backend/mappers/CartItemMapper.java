package com.literally.backend.mappers;

import com.literally.backend.dtos.CartItemDTO;
import com.literally.backend.entities.CartItem;
import com.literally.backend.entities.User;
import com.literally.backend.services.UserService;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@RequiredArgsConstructor
public class CartItemMapper {

    private final ProductMapper productMapper;
    private final UserService userService;

    public CartItemDTO mapToDto(CartItem entity){
        UUID userId = entity.getUser() != null ? entity.getUser().getId() : null;

        return CartItemDTO.builder()
                .userId(userId)
                .product(productMapper.mapToDto(entity.getProduct()))
                .quantity(entity.getQuantity())
                .build();
    }

    public CartItem mapToEntity(CartItemDTO dto){
        User user = dto.getUserId() != null ? userService.getById(dto.getUserId()) : null;

        return CartItem.builder()
                .user(user)
                .product(productMapper.mapToEntity(dto.getProduct()))
                .quantity(dto.getQuantity())
                .build();
    }
}
