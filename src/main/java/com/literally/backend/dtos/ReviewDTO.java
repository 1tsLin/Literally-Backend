package com.literally.backend.dtos;

import lombok.*;

import java.math.BigDecimal;
import java.util.Date;
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

    private Date creationDate;
    private Date updateDate;
}
