package org.lazydog.comicbox.controller;

import java.util.List;
import java.util.UUID;
import org.lazydog.comicbox.model.Imprint;
import org.lazydog.comicbox.service.ImprintService;
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
 * Imprint controller.
 * 
 * @author rjrjr
 */
@RestController
@RequestMapping(value = "/imprints", produces = {MediaType.APPLICATION_JSON_VALUE})
public class ImprintController {
    
    @Autowired
    private ImprintService service;

    @PostMapping("")
    public ResponseEntity<Imprint> createImprint(@RequestBody Imprint imprint) {
        return new ResponseEntity<>(this.service.createImprint(imprint), HttpStatus.OK);
    }

    @DeleteMapping("/{imprintId}")
    public ResponseEntity<?> deleteImprint(@PathVariable("imprintId") UUID imprintId) {
        this.service.deleteImprint(imprintId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/{imprintId}")
    public ResponseEntity<Imprint> findImprint(@PathVariable("imprintId") UUID imprintId) {
        return new ResponseEntity<>(this.service.findImprint(imprintId), HttpStatus.OK);
    }
    
    @GetMapping("")
    public ResponseEntity<List<Imprint>> findImprints() {
        return new ResponseEntity<>(this.service.findImprints(), HttpStatus.OK);
    }
         
    @PutMapping("/{imprintId}")
    public ResponseEntity<Imprint> updateImprint(@PathVariable("imprintId") UUID imprintId, @RequestBody Imprint imprint) {
        return new ResponseEntity<>(this.service.updateImprint(imprintId, imprint), HttpStatus.OK);
    }
}
