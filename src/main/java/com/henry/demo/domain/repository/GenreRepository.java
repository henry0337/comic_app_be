package com.henry.demo.domain.repository;

import com.henry.demo.domain.model.Genre;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GenreRepository extends JpaRepository<Genre, Integer> {}
