package com.literally.backend.controllers;

import com.literally.backend.dtos.ReviewDTO;
import com.literally.backend.services.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/reviews")
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;

    /*------------------------------------------------------------------------------------------------------------------
                                                  Review CRUD operations
    ------------------------------------------------------------------------------------------------------------------*/

    @GetMapping("/{reviewId}")
    public ReviewDTO getReview(@PathVariable UUID reviewId) {
        return reviewService.getById(reviewId);
    }

    @PostMapping
    public ReviewDTO createReview(@RequestBody ReviewDTO dto) {
        return reviewService.create(dto);
    }

    @PutMapping("/{reviewId}")
    public ReviewDTO updateReview(@PathVariable UUID reviewId, @RequestBody ReviewDTO dto) {
        return reviewService.update(reviewId, dto);
    }

    @DeleteMapping("/{reviewId}")
    public void deleteReview(@PathVariable UUID reviewId) {
        reviewService.delete(reviewId);
    }

}
