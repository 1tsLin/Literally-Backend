package com.literally.backend.dtos;

import lombok.*;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReviewDTO {
    private UUID id;

    private UUID userId;
    private UUID productId;

    private BigDecimal grade;
    private String comment;

    private Instant creationDate;
    private Instant updateDate;
}
