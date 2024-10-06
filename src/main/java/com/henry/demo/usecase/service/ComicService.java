package com.henry.demo.usecase.service;

import com.henry.demo.adapter.dto.ComicDTO;
import com.henry.demo.adapter.mapper.ComicMapper;
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
    private final ComicMapper mapper;

    public List<ComicDTO> getAll() {
        return repository.findAll().stream()
                .map(mapper::modelToDTO)
                .toList();
    }

    public ComicDTO getById(int id) {
        Comic comic = repository.findById(id).orElse(null);
        return mapper.modelToDTO(comic);
    }

    public Comic insert(ComicDTO comicDTO) {
        Comic comic = mapper.dtoToModel(comicDTO);
        return repository.save(comic);
    }

    public Comic update(int id, @NonNull ComicDTO comicDTO) {
        try {
            Optional<Comic> currentComic = repository.findById(id);
            Comic newComic = currentComic.get();

            newComic.setTitle(comicDTO.getTitle());
            newComic.setDescription(comicDTO.getDescription());
            newComic.setPoster(comicDTO.getPoster());
            newComic.setReleaseDate(comicDTO.getReleaseDate());
            newComic.setView(comicDTO.getView());
            newComic.setRating(comicDTO.getRating());
            newComic.setType(comicDTO.getType());
            newComic.setStatus(comicDTO.getStatus());
            newComic.setPublishedAt(comicDTO.getPublishedAt());
            newComic.setReview(comicDTO.getReview());

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
