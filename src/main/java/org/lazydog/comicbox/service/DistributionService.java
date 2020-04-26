package org.lazydog.comicbox.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import javax.transaction.Transactional;
import org.lazydog.comicbox.exception.EntityAlreadyExistsException;
import org.lazydog.comicbox.exception.EntityNotFoundException;
import org.lazydog.comicbox.model.Distribution;
import org.lazydog.comicbox.repository.DistributionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Distribution service.
 * 
 * @author rjrjr
 */
@Service
@Transactional
public class DistributionService {
    
    @Autowired
    private DistributionRepository repository;
    
    public Distribution createDistribution(final Distribution distribution) {
        Optional<Distribution> optional = this.repository.findByName(distribution.getName());
        if (optional.isPresent()) {
            throw new EntityAlreadyExistsException("The distribution identified by name " + distribution.getName() + " already exists.");
        }
        return this.repository.save(distribution);
    }
    
    public void deleteDistribution(final UUID distributionId) {
        Optional<Distribution> optional = this.repository.findById(distributionId);
        if (optional.isEmpty()) {
            throw new EntityNotFoundException("The distribution identified by ID " + distributionId + " is not found.");
        }
        this.repository.delete(optional.get());
    }
    
    public Distribution findDistribution(final UUID distributionId) {
        Optional<Distribution> optional = this.repository.findById(distributionId);
        if (optional.isEmpty()) {
            throw new EntityNotFoundException("The distribution identified by ID " + distributionId + " is not found.");
        }
        return optional.get();
    }
    
    public List<Distribution> findDistributions() {
        return this.repository.findAll();
    }
    
    public Distribution updateDistribution(final UUID distributionId, final Distribution distribution) {
        Optional<Distribution> optional = this.repository.findById(distributionId);
        if (optional.isEmpty()) {
            throw new EntityNotFoundException("The distribution identified by ID " + distributionId + " is not found.");
        }
        optional.get().setName(distribution.getName());
        return this.repository.save(optional.get());
    }
}
