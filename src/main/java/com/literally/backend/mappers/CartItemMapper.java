package com.literally.backend.mappers;

import com.literally.backend.dtos.CartItemDTO;
import com.literally.backend.entities.CartItem;
import com.literally.backend.entities.Product;
import com.literally.backend.entities.User;
import com.literally.backend.services.ProductService;
import com.literally.backend.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class CartItemMapper {

    private final UserService userService;
    private final ProductService productService;

    public CartItemDTO mapToDto(CartItem entity) {
        UUID userId = entity.getUser() != null ? entity.getUser().getId() : null;
        UUID productId = entity.getProduct() != null ? entity.getProduct().getId() : null;

        return CartItemDTO.builder()
                .userId(userId)
                .productId(productId)
                .quantity(entity.getQuantity())
                .build();
    }

    public CartItem mapToEntity(CartItemDTO dto) {
        User user = dto.getUserId() != null ? userService.getUserById(dto.getUserId()) : null;
        Product product = dto.getProductId() != null ? productService.getProductById(dto.getProductId()) : null;

        return CartItem.builder()
                .user(user)
                .product(product)
                .quantity(dto.getQuantity())
                .build();
    }
}
