package com.henry.demo.services;

import com.henry.demo.dto.ReviewDTO;
import com.henry.demo.dto.ReviewRequest;
import com.henry.demo.mappers.ReviewMapper;
import com.henry.demo.models.Review;
import com.henry.demo.repositories.ReviewRepository;
import io.sentry.Sentry;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepository repository;
    private final ReviewMapper reviewMapper;

    public List<ReviewDTO> getAll() {
        return repository.findAll().stream()
                .map(reviewMapper::modelToDTO)
                .toList();
    }

    public ReviewDTO getById(int id) {
        Review review = repository.findById(id).orElse(new Review());
        return reviewMapper.modelToDTO(review);
    }

    public List<ReviewDTO> getByComicId(int comicId) {
        List<Review> allReviewsBelongsToComic = repository.findByComicId(comicId);
        return allReviewsBelongsToComic.stream()
                .map(reviewMapper::modelToDTO)
                .toList();
    }

    public Review insert(ReviewRequest request) {
        Review review = reviewMapper.requestToModel(request);
        return repository.save(review);
    }

    public Review update(int id, @NonNull ReviewRequest review) {
        Optional<Review> currentReview = repository.findById(id);
        if (currentReview.isPresent()) {
            Review newReview = currentReview.get();

            newReview.setComment(review.getComment());
            newReview.setComicId(review.getComicId());
            newReview.setRating(review.getRating());
            newReview.setUserId(review.getUserId());

            return repository.save(newReview);
        } else {
            throw new NoSuchElementException("This review doesn't exist!");
        }
    }

    public void delete(int id) {
        repository.deleteById(id);
    }
}
