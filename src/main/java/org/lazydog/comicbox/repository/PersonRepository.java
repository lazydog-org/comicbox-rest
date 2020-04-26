package org.lazydog.comicbox.repository;

import java.util.Optional;
import java.util.UUID;
import org.lazydog.comicbox.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Person repository.
 * 
 * @author rjrjr
 */
public interface PersonRepository extends JpaRepository<Person, UUID> {
    
    Optional<Person> findByFirstNameAndLastName(String firstName, String lastName);
}
