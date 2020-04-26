package org.lazydog.comicbox.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import javax.transaction.Transactional;
import org.lazydog.comicbox.exception.EntityAlreadyExistsException;
import org.lazydog.comicbox.exception.EntityNotFoundException;
import org.lazydog.comicbox.model.Location;
import org.lazydog.comicbox.repository.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Location service.
 * 
 * @author rjrjr
 */
@Service
@Transactional
public class LocationService {
    
    @Autowired
    private LocationRepository repository;
    
    public Location createLocation(final Location location) {
        Optional<Location> optional = this.repository.findByNameAndUsername(location.getName(), location.getUsername());
        if (optional.isPresent()) {
            throw new EntityAlreadyExistsException("The location identified by name " + location.getName() + " and username " + location.getUsername() + " already exists.");
        }
        return this.repository.save(location);
    }
    
    public void deleteLocation(final UUID locationId) {
        Optional<Location> optional = this.repository.findById(locationId);
        if (optional.isEmpty()) {
            throw new EntityNotFoundException("The location identified by ID " + locationId + " is not found.");
        }
        this.repository.delete(optional.get());
    }
    
    public Location findLocation(final UUID locationId) {
        Optional<Location> optional = this.repository.findById(locationId);
        if (optional.isEmpty()) {
            throw new EntityNotFoundException("The location identified by ID " + locationId + " is not found.");
        }
        return optional.get();
    }
    
    public List<Location> findLocations() {
        return this.repository.findAll();
    }
    
    public Location updateLocation(final UUID locationId, final Location location) {
        Optional<Location> optional = this.repository.findById(locationId);
        if (optional.isEmpty()) {
            throw new EntityNotFoundException("The location identified by ID " + locationId + " is not found.");
        }
        optional.get().setName(location.getName());
        optional.get().setName(location.getUsername());
        return this.repository.save(optional.get());
    }
}
