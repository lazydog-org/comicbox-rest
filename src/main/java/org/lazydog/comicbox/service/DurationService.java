package org.lazydog.comicbox.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import javax.transaction.Transactional;
import org.lazydog.comicbox.exception.EntityAlreadyExistsException;
import org.lazydog.comicbox.exception.EntityNotFoundException;
import org.lazydog.comicbox.model.Duration;
import org.lazydog.comicbox.repository.DurationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Duration service.
 * 
 * @author rjrjr
 */
@Service
@Transactional
public class DurationService {
    
    @Autowired
    private DurationRepository repository;
    
    public Duration createDuration(final Duration duration) {
        Optional<Duration> optional = this.repository.findByName(duration.getName());
        if (optional.isPresent()) {
            throw new EntityAlreadyExistsException("The duration identified by name " + duration.getName() + " already exists.");
        }
        return this.repository.save(duration);
    }
    
    public void deleteDuration(final UUID durationId) {
        Optional<Duration> optional = this.repository.findById(durationId);
        if (optional.isEmpty()) {
            throw new EntityNotFoundException("The duration identified by ID " + durationId + " is not found.");
        }
        this.repository.delete(optional.get());
    }
    
    public Duration findDuration(final UUID durationId) {
        Optional<Duration> optional = this.repository.findById(durationId);
        if (optional.isEmpty()) {
            throw new EntityNotFoundException("The duration identified by ID " + durationId + " is not found.");
        }
        return optional.get();
    }
    
    public List<Duration> findDurations() {
        return this.repository.findAll();
    }
    
    public Duration updateDuration(final UUID durationId, final Duration duration) {
        Optional<Duration> optional = this.repository.findById(durationId);
        if (optional.isEmpty()) {
            throw new EntityNotFoundException("The duration identified by ID " + durationId + " is not found.");
        }
        optional.get().setName(duration.getName());
        return this.repository.save(optional.get());
    }
}
