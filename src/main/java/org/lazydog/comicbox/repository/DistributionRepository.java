package org.lazydog.comicbox.repository;

import java.util.Optional;
import java.util.UUID;
import org.lazydog.comicbox.model.Distribution;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Distribution repository.
 * 
 * @author rjrjr
 */
public interface DistributionRepository extends JpaRepository<Distribution, UUID> {
    
    Optional<Distribution> findByName(String name);
}
