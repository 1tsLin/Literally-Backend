package com.literally.backend.services;

import com.literally.backend.dtos.ReviewDTO;
import com.literally.backend.entities.Review;
import com.literally.backend.mappers.ReviewMapper;
import com.literally.backend.repositories.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final ReviewMapper reviewMapper;

    public ReviewDTO getById(UUID reviewId) {
        Review review = reviewRepository.findById(reviewId)
                .orElseThrow(() -> new RuntimeException("Review not found : " + reviewId));
        return reviewMapper.mapToDto(review);
    }

    @Transactional
    public ReviewDTO create(ReviewDTO dto) {
        if (dto == null)
            throw new IllegalArgumentException("Review create dto is null");

        /*-- Update review if already exists --*/
        Optional<Review> optReview = reviewRepository.findReviewByUserIdAndProductId(dto.getUserId(), dto.getProductId());
        if (optReview.isPresent())
            return update(optReview.get().getId(), dto);

        Review review = reviewRepository.save(reviewMapper.mapToEntity(dto));

        return reviewMapper.mapToDto(review);
    }

    @Transactional
    public ReviewDTO update(UUID reviewId, ReviewDTO dto) {
        if (dto == null)
            throw new IllegalArgumentException("Review update dto is null");

        if (!reviewRepository.existsById(reviewId))
            throw new RuntimeException("Review not found : " + reviewId);

        Review review = reviewMapper.mapToEntity(dto);
        review.setId(reviewId);

        reviewRepository.save(review);

        return reviewMapper.mapToDto(review);
    }

    @Transactional
    public void delete(UUID reviewId) {
        if (!reviewRepository.existsById(reviewId))
            throw new RuntimeException("Review not found : " + reviewId);

        reviewRepository.deleteById(reviewId);
    }
}
