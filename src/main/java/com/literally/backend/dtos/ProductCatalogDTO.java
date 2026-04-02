package com.literally.backend.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductCatalogDTO {
    private UUID productId;
    private BigDecimal price;
    private String title;
    private String authorName;
    private BigDecimal grade;
    private Integer reviews;
    private Boolean isFavorite;
    private UUID coverId;

    public ProductCatalogDTO(
            UUID productId,
            BigDecimal price,
            String title,
            String authorName,
            Integer grade,
            Long reviews,
            boolean isFavorite,
            UUID coverId
    ) {
        this.productId = productId;
        this.price = price;
        this.title = title;
        this.authorName = authorName;
        this.grade = BigDecimal.valueOf(grade);
        this.reviews = reviews != null ? reviews.intValue() : 0;
        this.isFavorite = isFavorite;
        this.coverId = coverId;
    }
}
