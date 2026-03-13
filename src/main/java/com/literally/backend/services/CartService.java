package com.literally.backend.services;

import com.literally.backend.dtos.CartItemDTO;
import com.literally.backend.entities.CartItem;
import com.literally.backend.mappers.CartItemMapper;
import com.literally.backend.repositories.CartItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CartService {

    private final CartItemRepository cartItemRepository;
    private final CartItemMapper cartItemMapper;

    /*------------------------------------------------------------------------------------------------------------------
                                                    Cart CRUD operations
    ------------------------------------------------------------------------------------------------------------------*/

    public Set<CartItemDTO> getCartByUserId(UUID userId) {
        Set<CartItem> cartItems = cartItemRepository.findAllByUserId(userId);

        return cartItems.stream().map(cartItemMapper::mapToDto).collect(Collectors.toSet());
    }

    @Transactional
    public CartItemDTO create(CartItemDTO dto) {
        if (dto == null)
            throw new RuntimeException("Cart item create dto is null");

        if (cartItemRepository.existsByUserIdAndProductId(dto.getUserId(), dto.getProductId()))
            return updateQuantity(dto.getUserId(), dto.getProductId(), dto.getQuantity());

        CartItem cartItem = cartItemRepository.save(cartItemMapper.mapToEntity(dto));

        return cartItemMapper.mapToDto(cartItem);
    }

    @Transactional
    public CartItemDTO updateQuantity(UUID userId, UUID productId, Integer quantity) {
        if (quantity <= 0)
            throw new IllegalArgumentException("Product quantity must be positive");

        CartItem cartItem = cartItemRepository.findByUserIdAndProductId(userId, productId)
                .orElseThrow(() -> new RuntimeException("Cart item not found : " + productId + ", for user : " + userId));

        cartItem.setQuantity(quantity);

        return cartItemMapper.mapToDto(cartItemRepository.save(cartItem));
    }

    @Transactional
    public void delete(UUID userId, UUID productId) {
        if (!cartItemRepository.existsByUserIdAndProductId(userId, productId))
            throw new RuntimeException("Cart item not found : " + productId + ", for user : " + userId);

        cartItemRepository.deleteByUserIdAndProductId(userId, productId);
    }

    /*------------------------------------------------------------------------------------------------------------------
                                                       Cart Checkout
    ------------------------------------------------------------------------------------------------------------------*/

}
