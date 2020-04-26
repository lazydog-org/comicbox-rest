package org.lazydog.comicbox.repository;

import java.util.Optional;
import java.util.UUID;
import org.lazydog.comicbox.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Category repository.
 * 
 * @author rjrjr
 */
public interface CategoryRepository extends JpaRepository<Category, UUID> {
    
    Optional<Category> findByName(String name);
}
