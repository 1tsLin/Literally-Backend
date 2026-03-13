package com.literally.backend.mappers;

import com.literally.backend.dtos.ReviewDTO;
import com.literally.backend.entities.Product;
import com.literally.backend.entities.Review;
import com.literally.backend.entities.User;
import com.literally.backend.services.ProductService;
import com.literally.backend.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class ReviewMapper {

    private final UserService userService;
    private final ProductService productService;

    public ReviewDTO mapToDto(Review entity) {
        UUID userId = entity.getUser() != null ? entity.getUser().getId() : null;
        UUID productId = entity.getProduct() != null ? entity.getProduct().getId() : null;

        return ReviewDTO.builder()
                .id(entity.getId())

                .userId(userId)
                .productId(productId)

                .grade(entity.getGrade())
                .comment(entity.getComment())

                .creationDate(entity.getCreationDate())
                .updateDate(entity.getUpdateDate())

                .build();
    }

    public Review mapToEntity(ReviewDTO dto) {
        User user = dto.getUserId() != null ? userService.getUserById(dto.getUserId()) : null;
        Product product = dto.getProductId() != null ? productService.getProductById(dto.getProductId()) : null;

        return Review.builder()
                .user(user)
                .product(product)

                .grade(dto.getGrade())
                .comment(dto.getComment())

                .creationDate(dto.getCreationDate())
                .updateDate(dto.getUpdateDate())

                .build();
    }
}
