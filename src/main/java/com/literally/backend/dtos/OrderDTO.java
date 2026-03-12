package com.literally.backend.dtos;

import com.literally.backend.enums.OrderStatusEnum;
import lombok.*;

import java.util.*;

@Getter
@Setter
public class OrderDTO {
    private UUID id;

    private OrderStatusEnum status;
    private UserDTO user;

    private Date creationDate;
    private Date updateDate;

    private Set<OrderItemDTO> items;
}
