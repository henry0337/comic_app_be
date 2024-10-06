package com.henry.demo.adapter.controller;

import com.henry.demo.adapter.dto.ReviewDTO;
import com.henry.demo.adapter.dto.request.ReviewRequest;
import com.henry.demo.domain.model.Review;
import com.henry.demo.usecase.service.ReviewService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reviews")
@CrossOrigin("*")
@RequiredArgsConstructor
@Tag(name = "Đánh giá (Review)")
public class ReviewController {

    private final ReviewService service;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ReviewDTO> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ReviewDTO getById(@PathVariable int id) {
        return service.getById(id);
    }

    @GetMapping("/{comicId}")
    @ResponseStatus(HttpStatus.OK)
    public List<ReviewDTO> getAllReviewsBelongsToComic(@PathVariable int comicId) {
        return service.getByComicId(comicId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Review insert(@RequestBody ReviewRequest review) {
        return service.insert(review);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Review update(@PathVariable int id, @RequestBody ReviewRequest request) {
        return service.update(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id) {
        service.delete(id);
    }
}
