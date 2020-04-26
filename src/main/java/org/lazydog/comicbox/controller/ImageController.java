package org.lazydog.comicbox.controller;

import java.util.List;
import java.util.UUID;
import org.lazydog.comicbox.model.Image;
import org.lazydog.comicbox.service.ImageService;
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
 * Image controller.
 * 
 * @author rjrjr
 */
@RestController
@RequestMapping(value = "/images", produces = {MediaType.APPLICATION_JSON_VALUE})
public class ImageController {
    
    @Autowired
    private ImageService service;

    @PostMapping("")
    public ResponseEntity<Image> createImage(@RequestBody Image image) {
        return new ResponseEntity<>(this.service.createImage(image), HttpStatus.OK);
    }

    @DeleteMapping("/{imageId}")
    public ResponseEntity<?> deleteImage(@PathVariable("imageId") UUID imageId) {
        this.service.deleteImage(imageId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/{imageId}")
    public ResponseEntity<Image> findImage(@PathVariable("imageId") UUID imageId) {
        return new ResponseEntity<>(this.service.findImage(imageId), HttpStatus.OK);
    }
    
    @GetMapping("")
    public ResponseEntity<List<Image>> findImages() {
        return new ResponseEntity<>(this.service.findImages(), HttpStatus.OK);
    }
         
    @PutMapping("/{imageId}")
    public ResponseEntity<Image> updateImage(@PathVariable("imageId") UUID imageId, @RequestBody Image image) {
        return new ResponseEntity<>(this.service.updateImage(imageId, image), HttpStatus.OK);
    }
}
