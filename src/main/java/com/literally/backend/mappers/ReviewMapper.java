package com.literally.backend.mappers;

import com.literally.backend.dtos.ReviewDTO;
import com.literally.backend.entities.Review;
import com.literally.backend.services.ProductService;
import com.literally.backend.services.UserService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ReviewMapper {

    private final UserService userService;
    private final ProductService productService;

    public ReviewDTO mapToDto(Review entity){
        return ReviewDTO.builder()
                .id(entity.getId())

                .userId(entity.getUser().getId())
                .productId(entity.getProduct().getId())

                .grade(entity.getGrade())
                .comment(entity.getComment())

                .creationDate(entity.getCreationDate())
                .updateDate(entity.getUpdateDate())

                .build();
    }

    public Review mapToEntity(ReviewDTO dto){
        return Review.builder()
                .id(dto.getId())

                .user(userService.getById(dto.getUserId()))
                .product(productService.getById(dto.getProductId()))

                .grade(dto.getGrade())
                .comment(dto.getComment())

                .creationDate(dto.getCreationDate())
                .updateDate(dto.getUpdateDate())

                .build();
    }
}
