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
    private Double grade;
    private Long reviews;
    private Boolean isFavorite;
    private UUID coverId;
}
