package org.lazydog.comicbox.controller;

import java.util.List;
import java.util.UUID;
import org.lazydog.comicbox.model.Actor;
import org.lazydog.comicbox.service.ActorService;
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
 * Actor controller.
 * 
 * @author rjrjr
 */
@RestController
@RequestMapping(value = "/actors", produces = {MediaType.APPLICATION_JSON_VALUE})
public class ActorController {
    
    @Autowired
    private ActorService service;

    @PostMapping("")
    public ResponseEntity<Actor> createActor(@RequestBody Actor actor) {
        return new ResponseEntity<>(this.service.createActor(actor), HttpStatus.OK);
    }

    @DeleteMapping("/{actorId}")
    public ResponseEntity<?> deleteActor(@PathVariable("actorId") UUID actorId) {
        this.service.deleteActor(actorId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/{actorId}")
    public ResponseEntity<Actor> findActor(@PathVariable("actorId") UUID actorId) {
        return new ResponseEntity<>(this.service.findActor(actorId), HttpStatus.OK);
    }
    
    @GetMapping("")
    public ResponseEntity<List<Actor>> findActors() {
        return new ResponseEntity<>(this.service.findActors(), HttpStatus.OK);
    }
         
    @PutMapping("/{actorId}")
    public ResponseEntity<Actor> updateActor(@PathVariable("actorId") UUID actorId, @RequestBody Actor actor) {
        return new ResponseEntity<>(this.service.updateActor(actorId, actor), HttpStatus.OK);
    }
}
