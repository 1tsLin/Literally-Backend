package com.literally.backend.entities;

import com.literally.backend.entities.composite_keys.OrderItemKey;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Entity
@Table(name = "order_items")
public class OrderItem {

    @EmbeddedId
    private OrderItemKey id;

    @ManyToOne
    @MapsId("orderId")
    @JoinColumn(name = "order_id")
    private Order order;

    @ManyToOne
    @MapsId("productId")
    @JoinColumn(name = "product_id")
    private Product product;

    private Integer quantity;

    @Column(precision = 5, scale = 2)
    private BigDecimal price;
}
