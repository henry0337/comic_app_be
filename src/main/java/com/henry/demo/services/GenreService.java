package com.henry.demo.services;

import com.henry.demo.models.Genre;
import com.henry.demo.repositories.GenreRepository;
import io.sentry.Sentry;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GenreService {

    private final GenreRepository repository;

    public List<Genre> getAll() {
        return repository.findAll();
    }

    public Genre getById(int id) {
        return repository.findById(id).orElse(null);
    }

    public Genre insert(Genre genre) {
        return repository.save(genre);
    }

    public Genre update(int id, @NonNull Genre genre) {
        try {
            Optional<Genre> currentGenre = repository.findById(id);
            Genre newGenre = currentGenre.get();

            newGenre.setName(genre.getName());

            return repository.save(newGenre);
        } catch (Exception e) {
            Sentry.captureException(e);
            Sentry.captureMessage(e.getLocalizedMessage());
        }
        return null;
    }

    public void delete(int id) {
        repository.deleteById(id);
    }
}
