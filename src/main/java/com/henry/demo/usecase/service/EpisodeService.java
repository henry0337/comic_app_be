package com.henry.demo.usecase.service;

import com.henry.demo.adapter.dto.EpisodeDTO;
import com.henry.demo.adapter.dto.request.EpisodeRequest;
import com.henry.demo.adapter.mapper.EpisodeMapper;
import com.henry.demo.domain.model.Episode;
import com.henry.demo.domain.repository.EpisodeRepository;
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
    private final EpisodeMapper mapper;

    public List<EpisodeDTO> getAll() {
        return repository.findAll().stream()
                .map(mapper::modelToDTO)
                .toList();
    }

    public EpisodeDTO getById(int id) {
        Episode episode = repository.findById(id).orElse(null);
        return mapper.modelToDTO(episode);
    }

    public Episode insert(EpisodeRequest request) {
        Episode episode = mapper.requestToModel(request);
        return repository.save(episode);
    }

    public Episode update(int id, @NonNull EpisodeRequest request) {
        try {
            Optional<Episode> currentEpisode = repository.findById(id);
            Episode newEpisode = currentEpisode.orElseThrow();

            newEpisode.setTitle(request.getTitle());
            newEpisode.setStatus(request.getStatus());
            newEpisode.setComicId(request.getComicId());
            newEpisode.setPublishedAt(request.getPublishedAt());

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
