package org.lazydog.comicbox.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import javax.transaction.Transactional;
import org.lazydog.comicbox.exception.EntityAlreadyExistsException;
import org.lazydog.comicbox.exception.EntityNotFoundException;
import org.lazydog.comicbox.model.Country;
import org.lazydog.comicbox.repository.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Country service.
 * 
 * @author rjrjr
 */
@Service
@Transactional
public class CountryService {
    
    @Autowired
    private CountryRepository repository;
    
    public Country createCountry(final Country country) {
        Optional<Country> optional = this.repository.findByName(country.getName());
        if (optional.isPresent()) {
            throw new EntityAlreadyExistsException("The country identified by name " + country.getName() + " already exists.");
        }
        return this.repository.save(country);
    }
    
    public void deleteCountry(final UUID countryId) {
        Optional<Country> optional = this.repository.findById(countryId);
        if (optional.isEmpty()) {
            throw new EntityNotFoundException("The country identified by ID " + countryId + " is not found.");
        }
        this.repository.delete(optional.get());
    }
    
    public Country findCountry(final UUID countryId) {
        Optional<Country> optional = this.repository.findById(countryId);
        if (optional.isEmpty()) {
            throw new EntityNotFoundException("The country identified by ID " + countryId + " is not found.");
        }
        return optional.get();
    }
    
    public List<Country> findCountries() {
        return this.repository.findAll();
    }
    
    public Country updateCountry(final UUID countryId, final Country country) {
        Optional<Country> optional = this.repository.findById(countryId);
        if (optional.isEmpty()) {
            throw new EntityNotFoundException("The country identified by ID " + countryId + " is not found.");
        }
        optional.get().setName(country.getName());
        return this.repository.save(optional.get());
    }
}
