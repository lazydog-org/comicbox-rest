package org.lazydog.comicbox.repository;

import java.util.Optional;
import java.util.UUID;
import org.lazydog.comicbox.model.Appearance;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Appearance repository.
 * 
 * @author rjrjr
 */
public interface AppearanceRepository extends JpaRepository<Appearance, UUID> {
    
    Optional<Appearance> findByClassification(String classification);
}
