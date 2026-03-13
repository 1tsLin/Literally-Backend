package com.literally.backend.entities.composite_keys;

import jakarta.persistence.Embeddable;
import lombok.*;

import java.io.Serializable;
import java.util.UUID;

@Getter
@Setter
@Embeddable
public class CartItemKey implements Serializable {
    private UUID userId;
    private UUID productId;
}
