package com.literally.backend.dtos;

import com.literally.backend.enums.OrderStatusEnum;
import lombok.*;

import java.util.*;

@Getter
@Setter
@Builder
public class OrderDTO {
    private UUID id;

    private OrderStatusEnum status;
    private UUID userId;

    private Date creationDate;
    private Date updateDate;

    private Set<OrderItemDTO> items;
}
