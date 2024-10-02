package com.henry.demo.services;

import com.henry.demo.models.Actor;
import com.henry.demo.repositories.ActorRepository;
import io.sentry.Sentry;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ActorService {

    private final ActorRepository repository;

    public List<Actor> getAll() {
        return repository.findAll();
    }

    public Actor getById(int id) {
        return repository.findById(id).orElse(null);
    }

    public Actor insert(Actor actor) {
        return repository.save(actor);
    }

    public Actor update(int id, @NonNull Actor actor) {
        try {
            Optional<Actor> currentActor = repository.findById(id);
            Actor newActor = currentActor.get();
            newActor.setName(actor.getName());
            newActor.setBirthday(actor.getBirthday());
            newActor.setAvatar(actor.getAvatar());
            newActor.setDescription(actor.getDescription());
            return repository.save(newActor);
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
