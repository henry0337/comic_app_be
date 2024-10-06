package com.henry.demo.usecase.service;

import com.henry.demo.adapter.dto.UserDTO;
import com.henry.demo.adapter.dto.request.UserRequest;
import com.henry.demo.adapter.mapper.UserMapper;
import com.henry.demo.domain.model.User;
import com.henry.demo.domain.repository.UserRepository;
import io.sentry.Sentry;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository repository;
    private final UserMapper mapper;

    public List<UserDTO> getAll() {
        return repository.findAll().stream()
                .map(mapper::modelToDTO)
                .toList();
    }

    public UserDTO findByEmail(String email) {
        User user = repository.findByEmail(email).orElse(new User());
        return mapper.modelToDTO(user);
    }

    public User insert(UserRequest request) {
        User savingUser = mapper.requestToModel(request);
        return repository.save(savingUser);
    }

    public User update(String email, @NonNull UserRequest request) {
        try {
            Optional<User> currentUser = repository.findByEmail(email);
            User newUser = currentUser.orElseThrow();

            newUser.setName(request.getName());
            newUser.setPassword(new BCryptPasswordEncoder().encode(request.getPassword()));
            newUser.setName(request.getName());
            newUser.setCanAuthenticate(request.getCanAuthenticate());
            newUser.setRole(request.getRole());
            newUser.setIsAccountExpired(request.getIsAccountExpired());
            newUser.setIsAccountLocked(request.getIsAccountLocked());
            newUser.setIsCredentialsExpired(request.getIsCredentialsExpired());

            return repository.save(newUser);
        } catch (EntityNotFoundException e) {
            Sentry.captureException(e);
            Sentry.captureMessage(e.getLocalizedMessage());
        }
        return null;
    }

    public void delete(String email) {
        repository.deleteByEmail(email);
    }

    @NonNull
    public final UserDetailsService userDetailsService() {
        return username -> {
            try {
                return repository.findByEmail(username).orElseThrow();
            } catch (UsernameNotFoundException e) {
                Sentry.captureException(e);
                Sentry.captureMessage(e.getLocalizedMessage());
                throw new UsernameNotFoundException("Không thể tìm thấy thông tin người dùng!\nTruy cập Sentry.io để biết rõ hơn.");
            }
        };
    }
}
