package org.lazydog.comicbox.repository;

import java.util.Optional;
import java.util.UUID;
import org.lazydog.comicbox.model.Imprint;
import org.lazydog.comicbox.model.Publisher;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Imprint repository.
 * 
 * @author rjrjr
 */
public interface ImprintRepository extends JpaRepository<Imprint, UUID> {
    
    Optional<Imprint> findByNameAndPublisher(String name, Publisher publisher);
}
