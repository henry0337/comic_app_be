package com.henry.demo.services;

import com.henry.demo.dto.ReviewDTO;
import com.henry.demo.mappers.ReviewMapper;
import com.henry.demo.models.Review;
import com.henry.demo.repositories.ReviewRepository;
import io.sentry.Sentry;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepository repository;
    private final ReviewMapper reviewMapper;

    public List<ReviewDTO> getAll() {
        return repository.findAll().stream()
                .map(reviewMapper::toDTO)
                .toList();
    }

    public ReviewDTO getById(int id) {
        Review review = repository.findById(id).orElse(new Review());
        return reviewMapper.toDTO(review);
    }

    public List<ReviewDTO> getByComicId(int comicId) {
        List<Review> allReviewsBelongsToComic = repository.findByComicId(comicId);
        return allReviewsBelongsToComic.stream()
                .map(reviewMapper::toDTO)
                .toList();
    }

    public Review insert(ReviewDTO reviewdto) {
        Review review = reviewMapper.toModel(reviewdto);
        return repository.save(review);
    }

    public Review update(int id, @NonNull Review review) {
        try {
            Optional<Review> currentReview = repository.findById(id);
            Review newReview = currentReview.get();

            newReview.setComment(review.getComment());
            newReview.setComicId(review.getComicId());
            newReview.setRating(review.getRating());
            newReview.setUserId(review.getUserId());

            return repository.save(newReview);
        } catch (Exception e) {
            Sentry.captureException(e);
            Sentry.captureMessage(e.getLocalizedMessage());
        }
        return null;
    }

    public void delete(int id) {
        repository.deleteById(id);
    }
}
