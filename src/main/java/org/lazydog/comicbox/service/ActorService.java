package org.lazydog.comicbox.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import javax.transaction.Transactional;
import org.lazydog.comicbox.exception.EntityAlreadyExistsException;
import org.lazydog.comicbox.exception.EntityNotFoundException;
import org.lazydog.comicbox.model.Actor;
import org.lazydog.comicbox.repository.ActorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Actor service.
 * 
 * @author rjrjr
 */
@Service
@Transactional
public class ActorService {
    
    @Autowired
    private ActorRepository repository;
    @Autowired
    private ImageService imageService;
    
    public Actor createActor(final Actor actor) {
        Optional<Actor> optional = this.repository.findByNameAndIteration(actor.getName(), actor.getIteration());
        if (optional.isPresent()) {
            throw new EntityAlreadyExistsException("The actor identified by name " + actor.getName() + " and iteration " + actor.getIteration() + " already exists.");
        }
        return this.repository.save(actor);
    }
    
    public void deleteActor(final UUID actorId) {
        Optional<Actor> optional = this.repository.findById(actorId);
        if (optional.isEmpty()) {
            throw new EntityNotFoundException("The actor identified by ID " + actorId + " is not found.");
        }
        this.repository.delete(optional.get());
    }
    
    public Actor findActor(final UUID actorId) {
        Optional<Actor> optional = this.repository.findById(actorId);
        if (optional.isEmpty()) {
            throw new EntityNotFoundException("The actor identified by ID " + actorId + " is not found.");
        }
        return optional.get();
    }
    
    public List<Actor> findActors() {
        return this.repository.findAll();
    }
    
    public Actor updateActor(final UUID actorId, final Actor actor) {
        Optional<Actor> optional = this.repository.findById(actorId);
        if (optional.isEmpty()) {
            throw new EntityNotFoundException("The actor identified by ID " + actorId + " is not found.");
        }
        if (actor.getImage() != null && actor.getImage().getId() != null) {
            optional.get().setImage(this.imageService.findImage(actor.getImage().getId()));
        }
        optional.get().setIteration(actor.getIteration());
        optional.get().setName(actor.getName());
        return this.repository.save(optional.get());
    }
}
