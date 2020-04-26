package org.lazydog.comicbox.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import javax.transaction.Transactional;
import org.lazydog.comicbox.exception.EntityAlreadyExistsException;
import org.lazydog.comicbox.exception.EntityNotFoundException;
import org.lazydog.comicbox.model.Category;
import org.lazydog.comicbox.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Category service.
 * 
 * @author rjrjr
 */
@Service
@Transactional
public class CategoryService {
    
    @Autowired
    private CategoryRepository repository;
    
    public Category createCategory(final Category category) {
        Optional<Category> optional = this.repository.findByName(category.getName());
        if (optional.isPresent()) {
            throw new EntityAlreadyExistsException("The category identified by name " + category.getName() + " already exists.");
        }
        return this.repository.save(category);
    }
    
    public void deleteCategory(final UUID categoryId) {
        Optional<Category> optional = this.repository.findById(categoryId);
        if (optional.isEmpty()) {
            throw new EntityNotFoundException("The category identified by ID " + categoryId + " is not found.");
        }
        this.repository.delete(optional.get());
    }
    
    public Category findCategory(final UUID categoryId) {
        Optional<Category> optional = this.repository.findById(categoryId);
        if (optional.isEmpty()) {
            throw new EntityNotFoundException("The category identified by ID " + categoryId + " is not found.");
        }
        return optional.get();
    }
    
    public List<Category> findCategories() {
        return this.repository.findAll();
    }
    
    public Category updateCategory(final UUID categoryId, final Category category) {
        Optional<Category> optional = this.repository.findById(categoryId);
        if (optional.isEmpty()) {
            throw new EntityNotFoundException("The category identified by ID " + categoryId + " is not found.");
        }
        optional.get().setName(category.getName());
        return this.repository.save(optional.get());
    }
}
