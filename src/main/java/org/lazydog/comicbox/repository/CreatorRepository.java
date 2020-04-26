package org.lazydog.comicbox.repository;

import java.util.Optional;
import java.util.UUID;
import org.lazydog.comicbox.model.Creator;
import org.lazydog.comicbox.model.Person;
import org.lazydog.comicbox.model.Profession;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Creator repository.
 * 
 * @author rjrjr
 */
public interface CreatorRepository extends JpaRepository<Creator, UUID> {
    
    Optional<Creator> findByPersonAndProfession(Person person, Profession profession);
}
