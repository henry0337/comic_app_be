package com.henry.demo.usecase.service;

import com.henry.demo.adapter.dto.ImageDTO;
import com.henry.demo.adapter.dto.request.ImageRequest;
import com.henry.demo.adapter.mapper.ImageMapper;
import com.henry.demo.domain.model.Image;
import com.henry.demo.domain.repository.ImageRepository;
import io.sentry.Sentry;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ImageService {
    private final ImageRepository repository;
    private final ImageMapper mapper;

    public List<ImageDTO> getAll() {
        return repository.findAll().stream()
                .map(mapper::modelToDTO)
                .toList();
    }

    public ImageDTO getById(int id) {
        Image image = repository.findById(id).orElse(null);
        return mapper.modelToDTO(image);
    }

    public Image insert(ImageDTO imageDTO) {
        Image image = mapper.dtoToModel(imageDTO);
        return repository.save(image);
    }

    public Image update(int id, @NonNull ImageRequest image) {
        try {
            Optional<Image> currentImage = repository.findById(id);
            Image newImage = currentImage.orElseThrow();

            newImage.setName(image.getName());
            newImage.setUrl(image.getUrl());
            newImage.setEpisodeId(image.getEpisodeId());

            return repository.save(newImage);
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
