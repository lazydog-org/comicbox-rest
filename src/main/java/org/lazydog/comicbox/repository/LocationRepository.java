package org.lazydog.comicbox.repository;

import java.util.Optional;
import java.util.UUID;
import org.lazydog.comicbox.model.Location;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Location repository.
 * 
 * @author rjrjr
 */
public interface LocationRepository extends JpaRepository<Location, UUID> {
    
    Optional<Location> findByNameAndUsername(String name, String username);
}
