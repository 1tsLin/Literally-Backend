package com.literally.backend.mappers;

import com.literally.backend.dtos.UserDTO;
import com.literally.backend.entities.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class UserMapper {

    public UserDTO mapToDto(User entity) {
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

                .favoriteIds(entity.getFavorites().stream()
                        .map(Product::getId)
                        .collect(Collectors.toSet()))
                .orderIds(entity.getOrders().stream()
                        .map(Order::getId)
                        .collect(Collectors.toSet()))
                .cartIds(entity.getCart().stream()
                        .map(CartItem::getId)
                        .collect(Collectors.toSet()))
                .reviewIds(entity.getReviews().stream()
                        .map(Review::getId)
                        .collect(Collectors.toSet()))

                .build();
    }

    public User mapToEntity(UserDTO dto) {
        return User.builder()
                .firstName(dto.getFirstName())
                .lastName(dto.getLastName())
                .address(dto.getAddress())

                .language(dto.getLanguage())
                .currency(dto.getCurrency())

                .email(dto.getEmail())
                .password(dto.getPassword())
                .isAdmin(dto.getIsAdmin())

                .build();
    }
}
