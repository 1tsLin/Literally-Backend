package com.literally.backend.entities.composite_keys;

import jakarta.persistence.Embeddable;
import lombok.*;

import java.io.Serializable;
import java.util.UUID;

@Getter
@Setter
@Embeddable
public class OrderItemKey implements Serializable {
    private UUID orderId;
    private UUID productId;
}
