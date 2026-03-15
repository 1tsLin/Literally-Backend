package com.literally.backend.dtos;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

@Getter
@Setter
@Builder
public class ReviewDTO {
    private UUID id;

    private UUID userId;
    private UUID productId;

    private BigDecimal grade;
    private String comment;

    private Instant creationDate;
    private Instant updateDate;
}
