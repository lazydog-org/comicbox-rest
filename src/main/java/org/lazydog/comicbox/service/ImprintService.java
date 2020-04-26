package org.lazydog.comicbox.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import javax.transaction.Transactional;
import org.lazydog.comicbox.exception.EntityAlreadyExistsException;
import org.lazydog.comicbox.exception.EntityMalformedException;
import org.lazydog.comicbox.exception.EntityNotFoundException;
import org.lazydog.comicbox.model.Imprint;
import org.lazydog.comicbox.repository.ImprintRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Imprint service.
 * 
 * @author rjrjr
 */
@Service
@Transactional
public class ImprintService {
    
    @Autowired
    private ImprintRepository repository;
    @Autowired
    private PublisherService publisherService;
    
    public Imprint createImprint(final Imprint imprint) {
        if (imprint.getPublisher() == null || imprint.getPublisher().getId() == null) {
            throw new EntityMalformedException("The publisher for the imprint is not specified.");
        }
        Optional<Imprint> optional = this.repository.findByNameAndPublisher(
                imprint.getName(), 
                this.publisherService.findPublisher(imprint.getPublisher().getId()));
        if (optional.isPresent()) {
            throw new EntityAlreadyExistsException("The imprint identified by name " + imprint.getName() + " and publisher " + imprint.getPublisher() + " already exists.");
        }
        return this.repository.save(imprint);
    }
    
    public void deleteImprint(final UUID imprintId) {
        Optional<Imprint> optional = this.repository.findById(imprintId);
        if (optional.isEmpty()) {
            throw new EntityNotFoundException("The imprint identified by ID " + imprintId + " is not found.");
        }
        this.repository.delete(optional.get());
    }
    
    public Imprint findImprint(final UUID imprintId) {
        Optional<Imprint> optional = this.repository.findById(imprintId);
        if (optional.isEmpty()) {
            throw new EntityNotFoundException("The imprint identified by ID " + imprintId + " is not found.");
        }
        return optional.get();
    }
    
    public List<Imprint> findImprints() {
        return this.repository.findAll();
    }
    
    public Imprint updateImprint(final UUID imprintId, final Imprint imprint) {
        Optional<Imprint> optional = this.repository.findById(imprintId);
        if (optional.isEmpty()) {
            throw new EntityNotFoundException("The imprint identified by ID " + imprintId + " is not found.");
        }
        if (imprint.getPublisher() == null || imprint.getPublisher().getId() == null) {
            throw new EntityMalformedException("The publisher for the imprint is not specified.");
        }
        optional.get().setName(imprint.getName());
        optional.get().setPublisher(this.publisherService.findPublisher(imprint.getPublisher().getId()));
        return this.repository.save(optional.get());
    }
}
