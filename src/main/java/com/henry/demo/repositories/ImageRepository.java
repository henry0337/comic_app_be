package com.henry.demo.repositories;

import com.henry.demo.models.Image;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepository extends JpaRepository<Image, Integer> {}
