package org.lazydog.comicbox.controller;

import java.util.List;
import java.util.UUID;
import org.lazydog.comicbox.model.Format;
import org.lazydog.comicbox.service.FormatService;
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
 * Format controller.
 * 
 * @author rjrjr
 */
@RestController
@RequestMapping(value = "/formats", produces = {MediaType.APPLICATION_JSON_VALUE})
public class FormatController {
    
    @Autowired
    private FormatService service;

    @PostMapping("")
    public ResponseEntity<Format> createFormat(@RequestBody Format format) {
        return new ResponseEntity<>(this.service.createFormat(format), HttpStatus.OK);
    }

    @DeleteMapping("/{formatId}")
    public ResponseEntity<?> deleteFormat(@PathVariable("formatId") UUID formatId) {
        this.service.deleteFormat(formatId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/{formatId}")
    public ResponseEntity<Format> findFormat(@PathVariable("formatId") UUID formatId) {
        return new ResponseEntity<>(this.service.findFormat(formatId), HttpStatus.OK);
    }
    
    @GetMapping("")
    public ResponseEntity<List<Format>> findFormats() {
        return new ResponseEntity<>(this.service.findFormats(), HttpStatus.OK);
    }
         
    @PutMapping("/{formatId}")
    public ResponseEntity<Format> updateFormat(@PathVariable("formatId") UUID formatId, @RequestBody Format format) {
        return new ResponseEntity<>(this.service.updateFormat(formatId, format), HttpStatus.OK);
    }
}
