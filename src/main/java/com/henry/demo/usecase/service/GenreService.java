package com.henry.demo.usecase.service;

import com.henry.demo.adapter.dto.GenreDTO;
import com.henry.demo.adapter.mapper.GenreMapper;
import com.henry.demo.domain.model.Genre;
import com.henry.demo.domain.repository.GenreRepository;
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
    private final GenreMapper mapper;

    public List<GenreDTO> getAll() {
        return repository.findAll().stream()
                .map(mapper::modelToDTO)
                .toList();
    }

    public GenreDTO getById(int id) {
        Genre genre = repository.findById(id).orElse(null);
        return mapper.modelToDTO(genre);
    }

    public Genre insert(GenreDTO genreDTO) {
        Genre genre = mapper.dtoToModel(genreDTO);
        return repository.save(genre);
    }

    public Genre update(int id, @NonNull GenreDTO genre) {
        try {
            Optional<Genre> currentGenre = repository.findById(id);
            Genre newGenre = currentGenre.orElseThrow();

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
