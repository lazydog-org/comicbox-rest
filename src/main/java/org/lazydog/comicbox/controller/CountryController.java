package org.lazydog.comicbox.controller;

import java.util.List;
import java.util.UUID;
import org.lazydog.comicbox.model.Country;
import org.lazydog.comicbox.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Country controller.
 * 
 * @author rjrjr
 */
@RestController
@RequestMapping(value = "/countries", produces = {MediaType.APPLICATION_JSON_VALUE})
public class CountryController {
    
    @Autowired
    private CountryService service;

    @PostMapping("")
    public ResponseEntity<Country> createCountry(@RequestBody Country country) {
        return new ResponseEntity<>(this.service.createCountry(country), HttpStatus.OK);
    }

    @DeleteMapping("/{countryId}")
    public ResponseEntity<?> deleteCountry(@PathVariable("countryId") UUID countryId) {
        this.service.deleteCountry(countryId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/{countryId}")
    public ResponseEntity<Country> findCountry(@PathVariable("countryId") UUID countryId) {
        return new ResponseEntity<>(this.service.findCountry(countryId), HttpStatus.OK);
    }
    
    @GetMapping("")
    public ResponseEntity<List<Country>> findCountries() {
        return new ResponseEntity<>(this.service.findCountries(), HttpStatus.OK);
    }
         
    @PutMapping("/{countryId}")
    public ResponseEntity<Country> updateCountry(@PathVariable("countryId") UUID countryId, @RequestBody Country country) {
        return new ResponseEntity<>(this.service.updateCountry(countryId, country), HttpStatus.OK);
    }
}
