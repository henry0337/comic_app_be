package com.henry.demo.repositories;

import com.henry.demo.models.Role;
import com.henry.demo.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, String> {
    Optional<User> findByEmail(String email);
    List<User> findByRole(Role role);
}
