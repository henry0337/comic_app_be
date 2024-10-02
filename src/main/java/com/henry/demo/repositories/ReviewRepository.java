package com.henry.demo.repositories;

import com.henry.demo.models.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review, Integer> {}
