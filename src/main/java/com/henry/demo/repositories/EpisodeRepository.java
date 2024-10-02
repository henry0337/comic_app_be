package com.henry.demo.repositories;

import com.henry.demo.models.Episode;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EpisodeRepository extends JpaRepository<Episode, Integer> {}
