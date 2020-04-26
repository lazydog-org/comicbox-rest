package org.lazydog.comicbox.repository;

import java.util.Optional;
import java.util.UUID;
import org.lazydog.comicbox.model.Profession;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Profession repository.
 * 
 * @author rjrjr
 */
public interface ProfessionRepository extends JpaRepository<Profession, UUID> {
    
    Optional<Profession> findByName(String name);
}
