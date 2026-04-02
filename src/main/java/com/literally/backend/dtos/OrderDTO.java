package com.literally.backend.dtos;

import com.literally.backend.enums.OrderStatusEnum;
import lombok.*;

import java.time.Instant;
import java.util.Set;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderDTO {
    private UUID id;

    private OrderStatusEnum status;
    private UUID userId;

    private Instant creationDate;
    private Instant updateDate;

    private Set<OrderItemDTO> items;
}
