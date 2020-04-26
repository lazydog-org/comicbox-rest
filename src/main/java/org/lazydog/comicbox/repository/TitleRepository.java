package org.lazydog.comicbox.repository;

import java.util.Optional;
import java.util.UUID;
import org.lazydog.comicbox.model.Title;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Title repository.
 * 
 * @author rjrjr
 */
public interface TitleRepository extends JpaRepository<Title, UUID> {
    
    Optional<Title> findByNameAndVolume(String name, Integer volume);
}
