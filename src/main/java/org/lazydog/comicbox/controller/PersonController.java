package org.lazydog.comicbox.controller;

import java.util.List;
import java.util.UUID;
import org.lazydog.comicbox.model.Person;
import org.lazydog.comicbox.service.PersonService;
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
 * Person controller.
 * 
 * @author rjrjr
 */
@RestController
@RequestMapping(value = "/persons", produces = {MediaType.APPLICATION_JSON_VALUE})
public class PersonController {
    
    @Autowired
    private PersonService service;

    @PostMapping("")
    public ResponseEntity<Person> createPerson(@RequestBody Person person) {
        return new ResponseEntity<>(this.service.createPerson(person), HttpStatus.OK);
    }

    @DeleteMapping("/{personId}")
    public ResponseEntity<?> deletePerson(@PathVariable("personId") UUID personId) {
        this.service.deletePerson(personId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/{personId}")
    public ResponseEntity<Person> findPerson(@PathVariable("personId") UUID personId) {
        return new ResponseEntity<>(this.service.findPerson(personId), HttpStatus.OK);
    }
    
    @GetMapping("")
    public ResponseEntity<List<Person>> findPersons() {
        return new ResponseEntity<>(this.service.findPersons(), HttpStatus.OK);
    }
         
    @PutMapping("/{personId}")
    public ResponseEntity<Person> updatePerson(@PathVariable("personId") UUID personId, @RequestBody Person person) {
        return new ResponseEntity<>(this.service.updatePerson(personId, person), HttpStatus.OK);
    }
}
