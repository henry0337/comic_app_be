package com.henry.demo.services;

import com.henry.demo.repositories.UserRepository;
import io.sentry.Sentry;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository repository;

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
