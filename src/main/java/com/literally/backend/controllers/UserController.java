package com.literally.backend.controllers;

import com.literally.backend.dtos.ProductDTO;
import com.literally.backend.dtos.UserDTO;
import com.literally.backend.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Set;
import java.util.UUID;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    /*------------------------------------------------------------------------------------------------------------------
                                                  User CRUD operations
    ------------------------------------------------------------------------------------------------------------------*/

    @GetMapping("/{userId}")
    public UserDTO getUser(@PathVariable UUID userId) {
        return userService.getById(userId);
    }

    @PostMapping
    public UserDTO createUser(@RequestBody UserDTO dto) {
        return userService.create(dto);
    }

    @PutMapping("/{userId}")
    public UserDTO updateUser(@PathVariable UUID userId, @RequestBody UserDTO dto) {
        return userService.update(userId, dto);
    }

    @DeleteMapping("{userId}")
    public void deleteUser(@PathVariable UUID userId) {
        userService.delete(userId);
    }

    /*------------------------------------------------------------------------------------------------------------------
                                              User favorite products operations
    ------------------------------------------------------------------------------------------------------------------*/

    @GetMapping("/{userId}/favorites")
    public Set<ProductDTO> getUserFavorites(@PathVariable UUID userId) {
        return userService.getFavorites(userId);
    }

    @PostMapping("/{userId}/favorites/{productId}")
    public void addFavoriteProduct(@PathVariable UUID userId, @PathVariable UUID productId) {
        userService.addFavoriteProduct(userId, productId);
    }

    @DeleteMapping("/{userId}/favorites/{productId}")
    public void removeFavoriteProduct(@PathVariable UUID userId, @PathVariable UUID productId) {
        userService.removeFavoriteProduct(userId, productId);
    }
}
