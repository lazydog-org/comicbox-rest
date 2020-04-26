package org.lazydog.comicbox.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import javax.transaction.Transactional;
import org.lazydog.comicbox.exception.EntityAlreadyExistsException;
import org.lazydog.comicbox.exception.EntityMalformedException;
import org.lazydog.comicbox.exception.EntityNotFoundException;
import org.lazydog.comicbox.model.Publisher;
import org.lazydog.comicbox.repository.PublisherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Publisher service.
 * 
 * @author rjrjr
 */
@Service
@Transactional
public class PublisherService {
    
    @Autowired
    private PublisherRepository repository;
    @Autowired
    private CountryService countryService;
    @Autowired
    private ImageService imageService;
    
    public Publisher createPublisher(final Publisher publisher) {
        if (publisher.getCountry() == null || publisher.getCountry().getId() == null) {
            throw new EntityMalformedException("The country for the publisher is not specified.");
        }
        Optional<Publisher> optional = this.repository.findByNameAndCountry(
                publisher.getName(),
                this.countryService.findCountry(publisher.getCountry().getId()));
        if (optional.isPresent()) {
            throw new EntityAlreadyExistsException("The publisher identified by name " + publisher.getName() + " and country " + publisher.getCountry() + " already exists.");
        }
        return this.repository.save(publisher);
    }
    
    public void deletePublisher(final UUID publisherId) {
        Optional<Publisher> optional = this.repository.findById(publisherId);
        if (optional.isEmpty()) {
            throw new EntityNotFoundException("The publisher identified by ID " + publisherId + " is not found.");
        }
        this.repository.delete(optional.get());
    }
    
    public Publisher findPublisher(final UUID publisherId) {
        Optional<Publisher> optional = this.repository.findById(publisherId);
        if (optional.isEmpty()) {
            throw new EntityNotFoundException("The publisher identified by ID " + publisherId + " is not found.");
        }
        return optional.get();
    }
    
    public List<Publisher> findPublishers() {
        return this.repository.findAll();
    }
    
    public Publisher updatePublisher(final UUID publisherId, final Publisher publisher) {
        Optional<Publisher> optional = this.repository.findById(publisherId);
        if (optional.isEmpty()) {
            throw new EntityNotFoundException("The publisher identified by ID " + publisherId + " is not found.");
        }
        if (publisher.getCountry() == null || publisher.getCountry().getId() == null) {
            throw new EntityMalformedException("The country for the publisher is not specified.");
        }
        optional.get().setCountry(this.countryService.findCountry(publisher.getCountry().getId()));
        if (publisher.getImage() != null && publisher.getImage().getId() != null) {
            optional.get().setImage(this.imageService.findImage(publisher.getImage().getId()));
        }
        optional.get().setName(publisher.getName());
        return this.repository.save(optional.get());
    }
}
