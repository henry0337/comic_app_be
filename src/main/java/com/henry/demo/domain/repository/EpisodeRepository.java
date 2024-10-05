package com.henry.demo.domain.repository;

import com.henry.demo.domain.model.Episode;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EpisodeRepository extends JpaRepository<Episode, Integer> {}
