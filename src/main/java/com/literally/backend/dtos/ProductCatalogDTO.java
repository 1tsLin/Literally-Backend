package com.literally.backend.dtos;

import java.math.BigDecimal;
import java.util.UUID;

public record ProductCatalogDTO(
        UUID productId,
        BigDecimal price,
        Integer quantity,
        String title,
        String authorName
) {
}
