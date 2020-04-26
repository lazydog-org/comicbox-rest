package org.lazydog.comicbox.repository;

import java.util.Optional;
import java.util.UUID;
import org.lazydog.comicbox.model.Format;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Format repository.
 * 
 * @author rjrjr
 */
public interface FormatRepository extends JpaRepository<Format, UUID> {
    
    Optional<Format> findByName(String name);
}
