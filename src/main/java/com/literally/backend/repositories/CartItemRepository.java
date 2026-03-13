package com.literally.backend.repositories;

import com.literally.backend.entities.CartItem;
import com.literally.backend.entities.composite_keys.CartItemKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, CartItemKey> {

    Set<CartItem> findAllByUserId(UUID userId);

    Optional<CartItem> findByUserIdAndProductId(UUID userId, UUID productId);

    boolean existsByUserIdAndProductId(UUID userId, UUID productId);

    void deleteByUserIdAndProductId(UUID userId, UUID productId);
}
