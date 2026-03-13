package com.literally.backend.mappers;

import com.literally.backend.dtos.UserDTO;
import com.literally.backend.entities.User;
import lombok.RequiredArgsConstructor;

import java.util.stream.Collectors;

@RequiredArgsConstructor
public class UserMapper {

    private final ProductMapper productMapper;
    private final OrderMapper orderMapper;
    private final CartItemMapper cartItemMapper;
    private final ReviewMapper reviewMapper;

    public UserDTO mapToDto(User entity){
        return UserDTO.builder()
                .id(entity.getId())

                .firstName(entity.getFirstName())
                .lastName(entity.getLastName())
                .address(entity.getAddress())

                .language(entity.getLanguage())
                .currency(entity.getCurrency())

                .email(entity.getEmail())
                .password(entity.getPassword())
                .isAdmin(entity.getIsAdmin())

                .favorites(entity.getFavorites().stream()
                        .map(productMapper::mapToDto)
                        .collect(Collectors.toSet()))
                .orders(entity.getOrders().stream()
                        .map(orderMapper::mapToDto)
                        .collect(Collectors.toSet()))
                .cart(entity.getCart().stream()
                        .map(cartItemMapper::mapToDto)
                        .collect(Collectors.toSet()))
                .reviews(entity.getReviews().stream()
                        .map(reviewMapper::mapToDto)
                        .collect(Collectors.toSet()))

                .build();
    }

    public User mapToEntity(UserDTO dto){
        return User.builder()
                .id(dto.getId())

                .firstName(dto.getFirstName())
                .lastName(dto.getLastName())
                .address(dto.getAddress())

                .language(dto.getLanguage())
                .currency(dto.getCurrency())

                .email(dto.getEmail())
                .password(dto.getPassword())
                .isAdmin(dto.getIsAdmin())

                .favorites(dto.getFavorites().stream()
                        .map(productMapper::mapToEntity)
                        .collect(Collectors.toSet()))
                .orders(dto.getOrders().stream()
                        .map(orderMapper::mapToEntity)
                        .collect(Collectors.toSet()))
                .cart(dto.getCart().stream()
                        .map(cartItemMapper::mapToEntity)
                        .collect(Collectors.toSet()))
                .reviews(dto.getReviews().stream()
                        .map(reviewMapper::mapToEntity)
                        .collect(Collectors.toSet()))

                .build();
    }
}
