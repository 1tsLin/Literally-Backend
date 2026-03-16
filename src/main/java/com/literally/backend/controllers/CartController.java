package com.literally.backend.controllers;

import com.literally.backend.dtos.CartItemDTO;
import com.literally.backend.services.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Set;
import java.util.UUID;

@RestController
@RequestMapping("/carts")
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;

    /*------------------------------------------------------------------------------------------------------------------
                                                    Cart CRUD operations
    ------------------------------------------------------------------------------------------------------------------*/

    @GetMapping("/{userId}")
    public Set<CartItemDTO> getUserCart(@PathVariable UUID userId) {
        return cartService.getCartByUserId(userId);
    }

    @PostMapping
    public CartItemDTO createCartItem(@RequestBody CartItemDTO dto) {
        return cartService.create(dto);
    }

    @PutMapping("/{userId}")
    public CartItemDTO updateCartItemQuantity(@PathVariable UUID userId,
                                              @RequestParam("productId") UUID productId,
                                              @RequestParam("quantity") Integer quantity) {
        return cartService.updateQuantity(userId, productId, quantity);
    }

    @DeleteMapping("/{userId}")
    public void deleteCartItem(@PathVariable UUID userId, @RequestParam("productId") UUID productId) {
        cartService.delete(userId, productId);
    }
}
