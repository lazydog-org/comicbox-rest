package org.lazydog.comicbox.controller;

import java.util.List;
import java.util.UUID;
import org.lazydog.comicbox.model.Appearance;
import org.lazydog.comicbox.service.AppearanceService;
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
 * Appearance controller.
 * 
 * @author rjrjr
 */
@RestController
@RequestMapping(value = "/appearances", produces = {MediaType.APPLICATION_JSON_VALUE})
public class AppearanceController {
    
    @Autowired
    private AppearanceService service;

    @PostMapping("")
    public ResponseEntity<Appearance> createAppearance(@RequestBody Appearance appearance) {
        return new ResponseEntity<>(this.service.createAppearance(appearance), HttpStatus.OK);
    }

    @DeleteMapping("/{appearanceId}")
    public ResponseEntity<?> deleteAppearance(@PathVariable("appearanceId") UUID appearanceId) {
        this.service.deleteAppearance(appearanceId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/{appearanceId}")
    public ResponseEntity<Appearance> findAppearance(@PathVariable("appearanceId") UUID appearanceId) {
        return new ResponseEntity<>(this.service.findAppearance(appearanceId), HttpStatus.OK);
    }
    
    @GetMapping("")
    public ResponseEntity<List<Appearance>> findAppearances() {
        return new ResponseEntity<>(this.service.findAppearances(), HttpStatus.OK);
    }
         
    @PutMapping("/{appearanceId}")
    public ResponseEntity<Appearance> updateAppearance(@PathVariable("appearanceId") UUID appearanceId, @RequestBody Appearance appearance) {
        return new ResponseEntity<>(this.service.updateAppearance(appearanceId, appearance), HttpStatus.OK);
    }
}
