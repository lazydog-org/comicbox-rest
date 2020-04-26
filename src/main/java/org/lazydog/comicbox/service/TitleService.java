package org.lazydog.comicbox.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import javax.transaction.Transactional;
import org.lazydog.comicbox.exception.EntityAlreadyExistsException;
import org.lazydog.comicbox.exception.EntityNotFoundException;
import org.lazydog.comicbox.model.Title;
import org.lazydog.comicbox.repository.TitleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Title service.
 * 
 * @author rjrjr
 */
@Service
@Transactional
public class TitleService {
    
    @Autowired
    private TitleRepository repository;
    
    public Title createTitle(final Title title) {
        Optional<Title> optional = this.repository.findByNameAndVolume(title.getName(), title.getVolume());
        if (optional.isPresent()) {
            throw new EntityAlreadyExistsException("The title identified by name " + title.getName() + " already exists.");
        }
        return this.repository.save(title);
    }
    
    public void deleteTitle(final UUID titleId) {
        Optional<Title> optional = this.repository.findById(titleId);
        if (optional.isEmpty()) {
            throw new EntityNotFoundException("The title identified by ID " + titleId + " is not found.");
        }
        this.repository.delete(optional.get());
    }
    
    public Title findTitle(final UUID titleId) {
        Optional<Title> optional = this.repository.findById(titleId);
        if (optional.isEmpty()) {
            throw new EntityNotFoundException("The title identified by ID " + titleId + " is not found.");
        }
        return optional.get();
    }
    
    public List<Title> findTitles() {
        return this.repository.findAll();
    }
    
    public Title updateTitle(final UUID titleId, final Title title) {
        Optional<Title> optional = this.repository.findById(titleId);
        if (optional.isEmpty()) {
            throw new EntityNotFoundException("The title identified by ID " + titleId + " is not found.");
        }
        // TODO: Complete this.
        optional.get().setName(title.getName());
        return this.repository.save(optional.get());
    }
}
