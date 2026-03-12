package com.literally.backend.entities;

import com.literally.backend.entities.composite_keys.CartItemKey;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Entity
@Table(name = "cart_items")
public class CartItem {

    @EmbeddedId
    CartItemKey id;

    @ManyToOne
    @MapsId("userId")
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @MapsId("productId")
    @JoinColumn(name = "product_id")
    private Product product;

    private Integer quantity;

}
