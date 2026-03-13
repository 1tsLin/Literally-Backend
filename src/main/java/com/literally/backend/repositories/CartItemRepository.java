package com.literally.backend.repositories;

import com.literally.backend.entities.CartItem;
import com.literally.backend.entities.composite_keys.CartItemKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, CartItemKey> {

    List<CartItem> findAllByUserId(UUID userId);

}
