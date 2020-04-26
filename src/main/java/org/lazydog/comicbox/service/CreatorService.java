package org.lazydog.comicbox.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import javax.transaction.Transactional;
import org.lazydog.comicbox.exception.EntityAlreadyExistsException;
import org.lazydog.comicbox.exception.EntityMalformedException;
import org.lazydog.comicbox.exception.EntityNotFoundException;
import org.lazydog.comicbox.model.Creator;
import org.lazydog.comicbox.model.Person;
import org.lazydog.comicbox.model.Profession;
import org.lazydog.comicbox.repository.CreatorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Creator service.
 * 
 * @author rjrjr
 */
@Service
@Transactional
public class CreatorService {
    
    @Autowired
    private CreatorRepository repository;
    @Autowired
    private PersonService personService;
    @Autowired
    private ProfessionService professionService;
    
    public Creator createCreator(final Creator creator) {
        if (creator.getPerson() == null || creator.getPerson().getId() == null) {
            throw new EntityMalformedException("The person for the creator is not specified.");
        }
        if (creator.getProfession() == null || creator.getProfession().getId() == null) {
            throw new EntityMalformedException("The profession for the creator is not specified.");
        }
        Person person = this.personService.findPerson(creator.getPerson().getId());
        Profession profession = this.professionService.findProfession(creator.getProfession().getId());
        Optional<Creator> optional = this.repository.findByPersonAndProfession(person, profession);
        if (optional.isPresent()) {
            throw new EntityAlreadyExistsException("The creator identified by person ID " + creator.getPerson().getId() + " and profession ID " + creator.getProfession().getId() + " already exists.");
        }
        creator.setPerson(person);
        creator.setProfession(profession);
        return this.repository.save(creator);
    }
    
    public void deleteCreator(final UUID creatorId) {
        Optional<Creator> optional = this.repository.findById(creatorId);
        if (optional.isEmpty()) {
            throw new EntityNotFoundException("The creator identified by ID " + creatorId + " is not found.");
        }
        this.repository.delete(optional.get());
    }
    
    public Creator findCreator(final UUID creatorId) {
        Optional<Creator> optional = this.repository.findById(creatorId);
        if (optional.isEmpty()) {
            throw new EntityNotFoundException("The creator identified by ID " + creatorId + " is not found.");
        }
        return optional.get();
    }
    
    public List<Creator> findCreators() {
        return this.repository.findAll();
    }
    
    public Creator updateCreator(final UUID creatorId, final Creator creator) {
        Optional<Creator> optional = this.repository.findById(creatorId);
        if (optional.isEmpty()) {
            throw new EntityNotFoundException("The creator identified by ID " + creatorId + " is not found.");
        }
        if (creator.getPerson() == null || creator.getPerson().getId() == null) {
            throw new EntityMalformedException("The person for the creator is not specified.");
        }
        if (creator.getProfession() == null || creator.getProfession().getId() == null) {
            throw new EntityMalformedException("The profession for the creator is not specified.");
        }
        optional.get().setPerson(this.personService.findPerson(creator.getPerson().getId()));
        optional.get().setProfession(this.professionService.findProfession(creator.getProfession().getId()));
        return this.repository.save(optional.get());
    }
}
