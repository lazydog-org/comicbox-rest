package org.lazydog.comicbox.repository;

import java.util.Optional;
import java.util.UUID;
import org.lazydog.comicbox.model.Country;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Country repository.
 * 
 * @author rjrjr
 */
public interface CountryRepository extends JpaRepository<Country, UUID> {
    
    Optional<Country> findByName(String name);
}
