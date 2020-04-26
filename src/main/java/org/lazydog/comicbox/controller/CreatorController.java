package org.lazydog.comicbox.controller;

import java.util.List;
import java.util.UUID;
import org.lazydog.comicbox.model.Creator;
import org.lazydog.comicbox.service.CreatorService;
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
 * Creator controller.
 * 
 * @author rjrjr
 */
@RestController
@RequestMapping(value = "/creators", produces = {MediaType.APPLICATION_JSON_VALUE})
public class CreatorController {
    
    @Autowired
    private CreatorService service;

    @PostMapping("")
    public ResponseEntity<Creator> createCreator(@RequestBody Creator creator) {
        return new ResponseEntity<>(this.service.createCreator(creator), HttpStatus.OK);
    }

    @DeleteMapping("/{creatorId}")
    public ResponseEntity<?> deleteCreator(@PathVariable("creatorId") UUID creatorId) {
        this.service.deleteCreator(creatorId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/{creatorId}")
    public ResponseEntity<Creator> findCreator(@PathVariable("creatorId") UUID creatorId) {
        return new ResponseEntity<>(this.service.findCreator(creatorId), HttpStatus.OK);
    }
    
    @GetMapping("")
    public ResponseEntity<List<Creator>> findCreators() {
        return new ResponseEntity<>(this.service.findCreators(), HttpStatus.OK);
    }
         
    @PutMapping("/{creatorId}")
    public ResponseEntity<Creator> updateCreator(@PathVariable("creatorId") UUID creatorId, @RequestBody Creator creator) {
        return new ResponseEntity<>(this.service.updateCreator(creatorId, creator), HttpStatus.OK);
    }
}
