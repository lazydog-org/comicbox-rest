package org.lazydog.comicbox.controller;

import java.util.List;
import java.util.UUID;
import org.lazydog.comicbox.model.Profession;
import org.lazydog.comicbox.service.ProfessionService;
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
 * Profession controller.
 * 
 * @author rjrjr
 */
@RestController
@RequestMapping(value = "/professions", produces = {MediaType.APPLICATION_JSON_VALUE})
public class ProfessionController {
    
    @Autowired
    private ProfessionService service;

    @PostMapping("")
    public ResponseEntity<Profession> createProfession(@RequestBody Profession profession) {
        return new ResponseEntity<>(this.service.createProfession(profession), HttpStatus.OK);
    }

    @DeleteMapping("/{professionId}")
    public ResponseEntity<?> deleteProfession(@PathVariable("professionId") UUID professionId) {
        this.service.deleteProfession(professionId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/{professionId}")
    public ResponseEntity<Profession> findProfession(@PathVariable("professionId") UUID professionId) {
        return new ResponseEntity<>(this.service.findProfession(professionId), HttpStatus.OK);
    }
    
    @GetMapping("")
    public ResponseEntity<List<Profession>> findProfessions() {
        return new ResponseEntity<>(this.service.findProfessions(), HttpStatus.OK);
    }
         
    @PutMapping("/{professionId}")
    public ResponseEntity<Profession> updateProfession(@PathVariable("professionId") UUID professionId, @RequestBody Profession profession) {
        return new ResponseEntity<>(this.service.updateProfession(professionId, profession), HttpStatus.OK);
    }
}
