package com.literally.backend.entities;

import com.literally.backend.enums.OrderStatusEnum;
import jakarta.persistence.*;
import lombok.*;

import java.util.*;

@Entity
@Table(name = "orders")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Enumerated(EnumType.STRING)
    private OrderStatusEnum status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    private Date creationDate;

    private Date updateDate;

    @OneToMany(mappedBy = "order")
    private Set<OrderItem> items = new HashSet<>();

}
