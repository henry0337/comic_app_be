package com.henry.demo.domain.repository;

import com.henry.demo.domain.model.Image;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepository extends JpaRepository<Image, Integer> {}
