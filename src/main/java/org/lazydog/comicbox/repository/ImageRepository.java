package org.lazydog.comicbox.repository;

import java.util.Optional;
import java.util.UUID;
import org.lazydog.comicbox.model.Image;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Image repository.
 * 
 * @author rjrjr
 */
public interface ImageRepository extends JpaRepository<Image, UUID> {
    
    Optional<Image> findByFilename(String filename);
}
