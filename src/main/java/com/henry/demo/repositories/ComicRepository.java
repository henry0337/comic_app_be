package com.henry.demo.repositories;

import com.henry.demo.models.Comic;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ComicRepository extends JpaRepository<Comic, Integer> {}
