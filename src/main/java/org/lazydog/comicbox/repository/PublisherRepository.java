package org.lazydog.comicbox.repository;

import java.util.Optional;
import java.util.UUID;
import org.lazydog.comicbox.model.Country;
import org.lazydog.comicbox.model.Publisher;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Publisher repository.
 * 
 * @author rjrjr
 */
public interface PublisherRepository extends JpaRepository<Publisher, UUID> {
    
    Optional<Publisher> findByNameAndCountry(String name, Country country);
}
