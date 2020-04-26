package org.lazydog.comicbox.repository;

import java.util.Optional;
import java.util.UUID;
import org.lazydog.comicbox.model.Actor;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Actor repository.
 * 
 * @author rjrjr
 */
public interface ActorRepository extends JpaRepository<Actor, UUID> {
    
    Optional<Actor> findByNameAndIteration(String name, Integer iteration);
}
