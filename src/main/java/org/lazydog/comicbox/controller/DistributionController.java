package org.lazydog.comicbox.controller;

import java.util.List;
import java.util.UUID;
import org.lazydog.comicbox.model.Distribution;
import org.lazydog.comicbox.service.DistributionService;
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
 * Distribution controller.
 * 
 * @author rjrjr
 */
@RestController
@RequestMapping(value = "/distributions", produces = {MediaType.APPLICATION_JSON_VALUE})
public class DistributionController {
    
    @Autowired
    private DistributionService service;

    @PostMapping("")
    public ResponseEntity<Distribution> createDistribution(@RequestBody Distribution distribution) {
        return new ResponseEntity<>(this.service.createDistribution(distribution), HttpStatus.OK);
    }

    @DeleteMapping("/{distributionId}")
    public ResponseEntity<?> deleteDistribution(@PathVariable("distributionId") UUID distributionId) {
        this.service.deleteDistribution(distributionId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/{distributionId}")
    public ResponseEntity<Distribution> findDistribution(@PathVariable("distributionId") UUID distributionId) {
        return new ResponseEntity<>(this.service.findDistribution(distributionId), HttpStatus.OK);
    }
    
    @GetMapping("")
    public ResponseEntity<List<Distribution>> findDistributions() {
        return new ResponseEntity<>(this.service.findDistributions(), HttpStatus.OK);
    }
         
    @PutMapping("/{distributionId}")
    public ResponseEntity<Distribution> updateDistribution(@PathVariable("distributionId") UUID distributionId, @RequestBody Distribution distribution) {
        return new ResponseEntity<>(this.service.updateDistribution(distributionId, distribution), HttpStatus.OK);
    }
}
