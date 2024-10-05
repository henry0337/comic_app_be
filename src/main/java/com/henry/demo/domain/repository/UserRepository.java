package com.henry.demo.domain.repository;

import com.henry.demo.domain.model.Role;
import com.henry.demo.domain.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, String> {
    Optional<User> findByEmail(String email);
    List<User> findByRole(Role role);
    void deleteByEmail(String email);
}
