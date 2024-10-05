package com.henry.demo.usecase.service;

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

    public List<Image> getAll() {
        return repository.findAll();
    }

    public Image getById(int id) {
        return repository.findById(id).orElse(null);
    }

    public Image insert(Image image) {
        return repository.save(image);
    }

    public Image update(int id, @NonNull Image image) {
        try {
            Optional<Image> currentImage = repository.findById(id);
            Image newImage = currentImage.get();

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
