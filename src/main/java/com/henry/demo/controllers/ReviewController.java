package com.henry.demo.controllers;

import com.henry.demo.dto.ReviewDTO;
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

    @GetMapping("/{comicId}")
    @ResponseStatus(HttpStatus.OK)
    public List<ReviewDTO> getAllBelongsToComic(@PathVariable int comicId) {
        return service.getByComicId(comicId);
    }
}
