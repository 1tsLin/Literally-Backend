package com.literally.backend.repositories;

import com.literally.backend.entities.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ReviewRepository extends JpaRepository<Review, UUID> {

    Optional<Review> findReviewByUserIdAndProductId(UUID userId, UUID productId);
}
