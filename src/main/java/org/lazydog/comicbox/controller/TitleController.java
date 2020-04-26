package org.lazydog.comicbox.controller;

import java.util.List;
import java.util.UUID;
import org.lazydog.comicbox.model.Title;
import org.lazydog.comicbox.service.TitleService;
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
 * Title controller.
 * 
 * @author rjrjr
 */
@RestController
@RequestMapping(value = "/titles", produces = {MediaType.APPLICATION_JSON_VALUE})
public class TitleController {
    
    @Autowired
    private TitleService service;

    @PostMapping("")
    public ResponseEntity<Title> createTitle(@RequestBody Title title) {
        return new ResponseEntity<>(this.service.createTitle(title), HttpStatus.OK);
    }

    @DeleteMapping("/{titleId}")
    public ResponseEntity<?> deleteTitle(@PathVariable("titleId") UUID titleId) {
        this.service.deleteTitle(titleId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/{titleId}")
    public ResponseEntity<Title> findTitle(@PathVariable("titleId") UUID titleId) {
        return new ResponseEntity<>(this.service.findTitle(titleId), HttpStatus.OK);
    }
    
    @GetMapping("")
    public ResponseEntity<List<Title>> findTitles() {
        return new ResponseEntity<>(this.service.findTitles(), HttpStatus.OK);
    }
         
    @PutMapping("/{titleId}")
    public ResponseEntity<Title> updateTitle(@PathVariable("titleId") UUID titleId, @RequestBody Title title) {
        return new ResponseEntity<>(this.service.updateTitle(titleId, title), HttpStatus.OK);
    }
}
