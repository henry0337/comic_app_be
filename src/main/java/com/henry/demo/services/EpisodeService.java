package com.henry.demo.services;

import com.henry.demo.models.Episode;
import com.henry.demo.repositories.EpisodeRepository;
import io.sentry.Sentry;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EpisodeService {
    private final EpisodeRepository repository;

    public List<Episode> getAll() {
        return repository.findAll();
    }

    public Episode getById(int id) {
        return repository.findById(id).orElse(null);
    }

    public Episode insert(Episode episode) {
        return repository.save(episode);
    }

    public Episode update(int id, @NonNull Episode episode) {
        try {
            Optional<Episode> currentEpisode = repository.findById(id);
            Episode newEpisode = currentEpisode.get();

            newEpisode.setTitle(episode.getTitle());
            newEpisode.setStatus(episode.getStatus());
            newEpisode.setComicId(episode.getComicId());
            newEpisode.setPublishedAt(episode.getPublishedAt());

            return repository.save(newEpisode);
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
