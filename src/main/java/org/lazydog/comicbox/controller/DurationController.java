package org.lazydog.comicbox.controller;

import java.util.List;
import java.util.UUID;
import org.lazydog.comicbox.model.Duration;
import org.lazydog.comicbox.service.DurationService;
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
 * Duration controller.
 * 
 * @author rjrjr
 */
@RestController
@RequestMapping(value = "/durations", produces = {MediaType.APPLICATION_JSON_VALUE})
public class DurationController {
    
    @Autowired
    private DurationService service;

    @PostMapping("")
    public ResponseEntity<Duration> createDuration(@RequestBody Duration duration) {
        return new ResponseEntity<>(this.service.createDuration(duration), HttpStatus.OK);
    }

    @DeleteMapping("/{durationId}")
    public ResponseEntity<?> deleteDuration(@PathVariable("durationId") UUID durationId) {
        this.service.deleteDuration(durationId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/{durationId}")
    public ResponseEntity<Duration> findDuration(@PathVariable("durationId") UUID durationId) {
        return new ResponseEntity<>(this.service.findDuration(durationId), HttpStatus.OK);
    }
    
    @GetMapping("")
    public ResponseEntity<List<Duration>> findDurations() {
        return new ResponseEntity<>(this.service.findDurations(), HttpStatus.OK);
    }
         
    @PutMapping("/{durationId}")
    public ResponseEntity<Duration> updateDuration(@PathVariable("durationId") UUID durationId, @RequestBody Duration duration) {
        return new ResponseEntity<>(this.service.updateDuration(durationId, duration), HttpStatus.OK);
    }
}
