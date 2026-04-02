package com.literally.backend.entities.composite_keys;

import jakarta.persistence.Embeddable;
import lombok.Data;

import java.io.Serializable;
import java.util.UUID;

@Data
@Embeddable
public class FavoriteKey implements Serializable {
    private UUID userId;
    private UUID productId;
}
