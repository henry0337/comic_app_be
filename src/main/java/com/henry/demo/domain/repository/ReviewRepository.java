package com.henry.demo.domain.repository;

import com.henry.demo.domain.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Integer> {

    @Query(value = "SELECT * FROM reviews WHERE comicId = ?1", nativeQuery = true)
    List<Review> findByComicId(int comicId);
}
