package org.lazydog.comicbox.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import javax.transaction.Transactional;
import org.lazydog.comicbox.exception.EntityAlreadyExistsException;
import org.lazydog.comicbox.exception.EntityNotFoundException;
import org.lazydog.comicbox.model.Person;
import org.lazydog.comicbox.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Person service.
 * 
 * @author rjrjr
 */
@Service
@Transactional
public class PersonService {
    
    @Autowired
    private PersonRepository repository;
    
    public Person createPerson(final Person person) {
        Optional<Person> optional = this.repository.findByFirstNameAndLastName(person.getFirstName(), person.getLastName());
        if (optional.isPresent()) {
            throw new EntityAlreadyExistsException("The person identified by first name " + person.getFirstName() + " and last name " + person.getLastName() + " already exists.");
        }
        return this.repository.save(person);
    }
    
    public void deletePerson(final UUID personId) {
        Optional<Person> optional = this.repository.findById(personId);
        if (optional.isEmpty()) {
            throw new EntityNotFoundException("The person identified by ID " + personId + " is not found.");
        }
        this.repository.delete(optional.get());
    }
    
    public Person findPerson(final UUID personId) {
        Optional<Person> optional = this.repository.findById(personId);
        if (optional.isEmpty()) {
            throw new EntityNotFoundException("The person identified by ID " + personId + " is not found.");
        }
        return optional.get();
    }
    
    public List<Person> findPersons() {
        return this.repository.findAll();
    }
    
    public Person updatePerson(final UUID personId, final Person person) {
        Optional<Person> optional = this.repository.findById(personId);
        if (optional.isEmpty()) {
            throw new EntityNotFoundException("The person identified by ID " + personId + " is not found.");
        }
        optional.get().setFirstName(person.getFirstName());
        optional.get().setFirstName(person.getLastName());
        return this.repository.save(optional.get());
    }
}
