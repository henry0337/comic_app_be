package com.henry.demo.controllers;

import com.henry.demo.dto.ReviewDTO;
import com.henry.demo.dto.ReviewRequest;
import com.henry.demo.models.Review;
import com.henry.demo.services.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reviews")
@CrossOrigin("*")
@RequiredArgsConstructor
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
