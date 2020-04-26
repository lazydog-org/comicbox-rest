package org.lazydog.comicbox.controller;

import java.util.List;
import java.util.UUID;
import org.lazydog.comicbox.model.Location;
import org.lazydog.comicbox.service.LocationService;
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
 * Location controller.
 * 
 * @author rjrjr
 */
@RestController
@RequestMapping(value = "/locations", produces = {MediaType.APPLICATION_JSON_VALUE})
public class LocationController {
    
    @Autowired
    private LocationService service;

    @PostMapping("")
    public ResponseEntity<Location> createLocation(@RequestBody Location location) {
        return new ResponseEntity<>(this.service.createLocation(location), HttpStatus.OK);
    }

    @DeleteMapping("/{locationId}")
    public ResponseEntity<?> deleteLocation(@PathVariable("locationId") UUID locationId) {
        this.service.deleteLocation(locationId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/{locationId}")
    public ResponseEntity<Location> findLocation(@PathVariable("locationId") UUID locationId) {
        return new ResponseEntity<>(this.service.findLocation(locationId), HttpStatus.OK);
    }
    
    @GetMapping("")
    public ResponseEntity<List<Location>> findLocations() {
        return new ResponseEntity<>(this.service.findLocations(), HttpStatus.OK);
    }
         
    @PutMapping("/{locationId}")
    public ResponseEntity<Location> updateLocation(@PathVariable("locationId") UUID locationId, @RequestBody Location location) {
        return new ResponseEntity<>(this.service.updateLocation(locationId, location), HttpStatus.OK);
    }
}
