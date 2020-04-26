package org.lazydog.comicbox.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import javax.transaction.Transactional;
import org.lazydog.comicbox.exception.EntityAlreadyExistsException;
import org.lazydog.comicbox.exception.EntityNotFoundException;
import org.lazydog.comicbox.model.Appearance;
import org.lazydog.comicbox.repository.AppearanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Appearance service.
 * 
 * @author rjrjr
 */
@Service
@Transactional
public class AppearanceService {
    
    @Autowired
    private AppearanceRepository repository;
    
    public Appearance createAppearance(final Appearance appearance) {
        Optional<Appearance> optional = this.repository.findByClassification(appearance.getClassification());
        if (optional.isPresent()) {
            throw new EntityAlreadyExistsException("The appearance identified by classification " + appearance.getClassification() + " already exists.");
        }
        return this.repository.save(appearance);
    }
    
    public void deleteAppearance(final UUID appearanceId) {
        Optional<Appearance> optional = this.repository.findById(appearanceId);
        if (optional.isEmpty()) {
            throw new EntityNotFoundException("The appearance identified by ID " + appearanceId + " is not found.");
        }
        this.repository.delete(optional.get());
    }
    
    public Appearance findAppearance(final UUID appearanceId) {
        Optional<Appearance> optional = this.repository.findById(appearanceId);
        if (optional.isEmpty()) {
            throw new EntityNotFoundException("The appearance identified by ID " + appearanceId + " is not found.");
        }
        return optional.get();
    }
    
    public List<Appearance> findAppearances() {
        return this.repository.findAll();
    }
    
    public Appearance updateAppearance(final UUID appearanceId, final Appearance appearance) {
        Optional<Appearance> optional = this.repository.findById(appearanceId);
        if (optional.isEmpty()) {
            throw new EntityNotFoundException("The appearance identified by ID " + appearanceId + " is not found.");
        }
        optional.get().setClassification(appearance.getClassification());
        return this.repository.save(optional.get());
    }
}
