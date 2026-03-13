package com.literally.backend.dtos;

import com.literally.backend.entities.composite_keys.OrderItemKey;
import com.literally.backend.enums.OrderStatusEnum;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@Builder
public class OrderDTO {
    private UUID id;

    private OrderStatusEnum status;
    private UUID userId;

    private Date creationDate;
    private Date updateDate;

    private Set<OrderItemKey> itemIds;
}
