package org.lazydog.comicbox.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import javax.transaction.Transactional;
import org.lazydog.comicbox.exception.EntityAlreadyExistsException;
import org.lazydog.comicbox.exception.EntityNotFoundException;
import org.lazydog.comicbox.model.Profession;
import org.lazydog.comicbox.repository.ProfessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Profession service.
 * 
 * @author rjrjr
 */
@Service
@Transactional
public class ProfessionService {
    
    @Autowired
    private ProfessionRepository repository;
    
    public Profession createProfession(final Profession profession) {
        Optional<Profession> optional = this.repository.findByName(profession.getName());
        if (optional.isPresent()) {
            throw new EntityAlreadyExistsException("The profession identified by name " + profession.getName() + " already exists.");
        }
        return this.repository.save(profession);
    }
    
    public void deleteProfession(final UUID professionId) {
        Optional<Profession> optional = this.repository.findById(professionId);
        if (optional.isEmpty()) {
            throw new EntityNotFoundException("The profession identified by ID " + professionId + " is not found.");
        }
        this.repository.delete(optional.get());
    }
    
    public Profession findProfession(final UUID professionId) {
        Optional<Profession> optional = this.repository.findById(professionId);
        if (optional.isEmpty()) {
            throw new EntityNotFoundException("The profession identified by ID " + professionId + " is not found.");
        }
        return optional.get();
    }
    
    public List<Profession> findProfessions() {
        return this.repository.findAll();
    }
    
    public Profession updateProfession(final UUID professionId, final Profession profession) {
        Optional<Profession> optional = this.repository.findById(professionId);
        if (optional.isEmpty()) {
            throw new EntityNotFoundException("The profession identified by ID " + professionId + " is not found.");
        }
        optional.get().setName(profession.getName());
        return this.repository.save(optional.get());
    }
}
