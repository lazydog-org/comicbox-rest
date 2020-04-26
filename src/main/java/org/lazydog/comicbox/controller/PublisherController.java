package org.lazydog.comicbox.controller;

import java.util.List;
import java.util.UUID;
import org.lazydog.comicbox.model.Publisher;
import org.lazydog.comicbox.service.PublisherService;
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
 * Publisher controller.
 * 
 * @author rjrjr
 */
@RestController
@RequestMapping(value = "/publishers", produces = {MediaType.APPLICATION_JSON_VALUE})
public class PublisherController {
    
    @Autowired
    private PublisherService service;

    @PostMapping("")
    public ResponseEntity<Publisher> createPublisher(@RequestBody Publisher publisher) {
        return new ResponseEntity<>(this.service.createPublisher(publisher), HttpStatus.OK);
    }

    @DeleteMapping("/{publisherId}")
    public ResponseEntity<?> deletePublisher(@PathVariable("publisherId") UUID publisherId) {
        this.service.deletePublisher(publisherId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/{publisherId}")
    public ResponseEntity<Publisher> findPublisher(@PathVariable("publisherId") UUID publisherId) {
        return new ResponseEntity<>(this.service.findPublisher(publisherId), HttpStatus.OK);
    }
    
    @GetMapping("")
    public ResponseEntity<List<Publisher>> findPublishers() {
        return new ResponseEntity<>(this.service.findPublishers(), HttpStatus.OK);
    }
         
    @PutMapping("/{publisherId}")
    public ResponseEntity<Publisher> updatePublisher(@PathVariable("publisherId") UUID publisherId, @RequestBody Publisher publisher) {
        return new ResponseEntity<>(this.service.updatePublisher(publisherId, publisher), HttpStatus.OK);
    }
}
