package com.henry.demo.domain.repository;

import com.henry.demo.domain.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Integer> {

    // Nếu bạn không quen với cú pháp của JPQL (Jakarta Persistence Query Language) như mình
    // thì hãy để `nativeQuery` của @Query là `true` để có thể sử dụng lệnh SQL của loại cơ sở dữ liệu bạn đang dùng.
    @Query(value = "SELECT * FROM reviews WHERE comicId = ?1", nativeQuery = true)
    List<Review> findByComicId(int comicId);
}
