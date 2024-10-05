package com.henry.demo.usecase.service;

import com.henry.demo.domain.model.Comic;
import com.henry.demo.domain.repository.ComicRepository;
import io.sentry.Sentry;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ComicService {
    private final ComicRepository repository;

    public List<Comic> getAll() {
        return repository.findAll();
    }

    public Comic getById(int id) {
        return repository.findById(id).orElse(null);
    }

    public Comic insert(Comic comic) {
        return repository.save(comic);
    }

    public Comic update(int id, @NonNull Comic comic) {
        try {
            Optional<Comic> currentComic = repository.findById(id);
            Comic newComic = currentComic.get();

            newComic.setTitle(comic.getTitle());
            newComic.setDescription(comic.getDescription());
            newComic.setPoster(comic.getPoster());
            newComic.setReleaseDate(comic.getReleaseDate());
            newComic.setView(comic.getView());
            newComic.setRating(comic.getRating());
            newComic.setType(comic.getType());
            newComic.setStatus(comic.getStatus());
            newComic.setPublishedAt(comic.getPublishedAt());
            newComic.setReview(comic.getReview());

            return repository.save(newComic);
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
