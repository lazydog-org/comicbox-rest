package org.lazydog.comicbox.repository;

import java.util.Optional;
import java.util.UUID;
import org.lazydog.comicbox.model.Duration;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Duration repository.
 * 
 * @author rjrjr
 */
public interface DurationRepository extends JpaRepository<Duration, UUID> {
    
    Optional<Duration> findByName(String name);
}
