package com.henry.demo.domain.repository;

import com.henry.demo.domain.model.Actor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ActorRepository extends JpaRepository<Actor, Integer> {}
