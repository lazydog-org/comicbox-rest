package org.lazydog.comicbox.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import javax.transaction.Transactional;
import org.lazydog.comicbox.exception.EntityAlreadyExistsException;
import org.lazydog.comicbox.exception.EntityNotFoundException;
import org.lazydog.comicbox.model.Format;
import org.lazydog.comicbox.repository.FormatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Format service.
 * 
 * @author rjrjr
 */
@Service
@Transactional
public class FormatService {
    
    @Autowired
    private FormatRepository repository;
    
    public Format createFormat(final Format format) {
        Optional<Format> optional = this.repository.findByName(format.getName());
        if (optional.isPresent()) {
            throw new EntityAlreadyExistsException("The format identified by name " + format.getName() + " already exists.");
        }
        return this.repository.save(format);
    }
    
    public void deleteFormat(final UUID formatId) {
        Optional<Format> optional = this.repository.findById(formatId);
        if (optional.isEmpty()) {
            throw new EntityNotFoundException("The format identified by ID " + formatId + " is not found.");
        }
        this.repository.delete(optional.get());
    }
    
    public Format findFormat(final UUID formatId) {
        Optional<Format> optional = this.repository.findById(formatId);
        if (optional.isEmpty()) {
            throw new EntityNotFoundException("The format identified by ID " + formatId + " is not found.");
        }
        return optional.get();
    }
    
    public List<Format> findFormats() {
        return this.repository.findAll();
    }
    
    public Format updateFormat(final UUID formatId, final Format format) {
        Optional<Format> optional = this.repository.findById(formatId);
        if (optional.isEmpty()) {
            throw new EntityNotFoundException("The format identified by ID " + formatId + " is not found.");
        }
        optional.get().setName(format.getName());
        return this.repository.save(optional.get());
    }
}
