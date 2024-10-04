package com.henry.demo.services;

import com.henry.demo.dto.UserDTO;
import com.henry.demo.holders.requests.UserRequest;
import com.henry.demo.mappers.UserMapper;
import com.henry.demo.models.User;
import com.henry.demo.repositories.UserRepository;
import io.sentry.Sentry;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public User update(int id, UserRequest request) {
        // TODO: Tiếp tục triển khai
        return null;
    }

    public void delete(String email) {
        repository.deleteByEmail(email);
    }

    @NonNull
    public final UserDetailsService userDetailsService() {
        return username -> {
            try {
                return repository.findByEmail(username).orElse(null);
            } catch (UsernameNotFoundException e) {
                Sentry.captureException(e);
                Sentry.captureMessage(e.getLocalizedMessage());
                throw new UsernameNotFoundException("Không thể tìm thấy thông tin người dùng!\nTruy cập Sentry.io để biết rõ hơn.");
            }
        };
    }
}
