package com.henry.demo.usecase.service;

import com.henry.demo.adapter.dto.ActorDTO;
import com.henry.demo.adapter.mapper.ActorMapper;
import com.henry.demo.domain.model.Actor;
import com.henry.demo.domain.repository.ActorRepository;
import io.sentry.Sentry;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ActorService {

    private final ActorRepository repository;
    private final ActorMapper mapper;

    public List<ActorDTO> getAll() {
        return repository.findAll().stream()
                .map(mapper::modelToDTO)
                .toList();
    }

    public ActorDTO getById(int id) {
        Actor actor = repository.findById(id).orElseThrow(() -> {
            Sentry.captureException(new EntityNotFoundException("Không tìm thấy thông tin về nhân vật này!"));
            return new EntityNotFoundException();
        });

        return mapper.modelToDTO(actor);
    }

    public Actor insert(ActorDTO actorDTO) {
        Actor actor = mapper.dtoToModel(actorDTO);
        return repository.save(actor);
    }

    public Actor update(int id, @NonNull ActorDTO actor) {
        try {
            Optional<Actor> currentActor = repository.findById(id);
            Actor newActor = currentActor.get();

            newActor.setName(actor.getName());
            newActor.setBirthday(actor.getBirthday());
            newActor.setAvatar(actor.getAvatar());
            newActor.setDescription(actor.getDescription());

            return repository.save(newActor);
        } catch (EntityNotFoundException e) {
            Sentry.captureException(e);
            Sentry.captureMessage(e.getLocalizedMessage());
        }
        return null;
    }

    public void delete(int id) {
        repository.deleteById(id);
    }
}
