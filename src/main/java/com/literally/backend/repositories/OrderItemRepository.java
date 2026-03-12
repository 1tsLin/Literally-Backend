package com.literally.backend.repositories;

import com.literally.backend.entities.OrderItem;
import com.literally.backend.entities.composite_keys.OrderItemKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, OrderItemKey> {

    List<OrderItem> findAllByOrderId(UUID orderId);

}
