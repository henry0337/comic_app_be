package com.henry.demo.domain.repository;

import com.henry.demo.domain.model.Comic;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ComicRepository extends JpaRepository<Comic, Integer> {}
