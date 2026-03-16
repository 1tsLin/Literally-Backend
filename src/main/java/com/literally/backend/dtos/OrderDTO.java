package com.literally.backend.dtos;

import com.literally.backend.enums.OrderStatusEnum;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@Builder
public class OrderDTO {
    private UUID id;

    private OrderStatusEnum status;
    private UUID userId;

    private Instant creationDate;
    private Instant updateDate;

    private Set<OrderItemDTO> items;
}
