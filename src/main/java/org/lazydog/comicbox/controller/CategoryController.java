package org.lazydog.comicbox.controller;

import java.util.List;
import java.util.UUID;
import org.lazydog.comicbox.model.Category;
import org.lazydog.comicbox.service.CategoryService;
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
 * Category controller.
 * 
 * @author rjrjr
 */
@RestController
@RequestMapping(value = "/categories", produces = {MediaType.APPLICATION_JSON_VALUE})
public class CategoryController {
    
    @Autowired
    private CategoryService service;

    @PostMapping("")
    public ResponseEntity<Category> createCategory(@RequestBody Category category) {
        return new ResponseEntity<>(this.service.createCategory(category), HttpStatus.OK);
    }

    @DeleteMapping("/{categoryId}")
    public ResponseEntity<?> deleteCategory(@PathVariable("categoryId") UUID categoryId) {
        this.service.deleteCategory(categoryId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/{categoryId}")
    public ResponseEntity<Category> findCategory(@PathVariable("categoryId") UUID categoryId) {
        return new ResponseEntity<>(this.service.findCategory(categoryId), HttpStatus.OK);
    }
    
    @GetMapping("")
    public ResponseEntity<List<Category>> findCategories() {
        return new ResponseEntity<>(this.service.findCategories(), HttpStatus.OK);
    }
         
    @PutMapping("/{categoryId}")
    public ResponseEntity<Category> updateCategory(@PathVariable("categoryId") UUID categoryId, @RequestBody Category category) {
        return new ResponseEntity<>(this.service.updateCategory(categoryId, category), HttpStatus.OK);
    }
}
