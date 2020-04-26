package org.lazydog.comicbox.repository;

import java.util.UUID;
import org.lazydog.comicbox.model.Grade;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Grade repository.
 * 
 * @author rjrjr
 */
public interface GradeRepository extends JpaRepository<Grade, UUID> {
    
}
