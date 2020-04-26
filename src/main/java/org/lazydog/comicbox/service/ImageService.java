package org.lazydog.comicbox.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import javax.transaction.Transactional;
import org.lazydog.comicbox.exception.EntityAlreadyExistsException;
import org.lazydog.comicbox.exception.EntityNotFoundException;
import org.lazydog.comicbox.model.Image;
import org.lazydog.comicbox.repository.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Image service.
 * 
 * @author rjrjr
 */
@Service
@Transactional
public class ImageService {
    
    @Autowired
    private ImageRepository repository;
    
    public Image createImage(final Image image) {
        Optional<Image> optional = this.repository.findByFilename(image.getFilename());
        if (optional.isPresent()) {
            throw new EntityAlreadyExistsException("The image identified by filename " + image.getFilename() + " already exists.");
        }
        return this.repository.save(image);
    }
    
    public void deleteImage(final UUID imageId) {
        Optional<Image> optional = this.repository.findById(imageId);
        if (optional.isEmpty()) {
            throw new EntityNotFoundException("The image identified by ID " + imageId + " is not found.");
        }
        this.repository.delete(optional.get());
    }
    
    public Image findImage(final UUID imageId) {
        Optional<Image> optional = this.repository.findById(imageId);
        if (optional.isEmpty()) {
            throw new EntityNotFoundException("The image identified by ID " + imageId + " is not found.");
        }
        return optional.get();
    }
    
    public List<Image> findImages() {
        return this.repository.findAll();
    }
    
    public Image updateImage(final UUID imageId, final Image image) {
        Optional<Image> optional = this.repository.findById(imageId);
        if (optional.isEmpty()) {
            throw new EntityNotFoundException("The image identified by ID " + imageId + " is not found.");
        }
        optional.get().setFilename(image.getFilename());
        return this.repository.save(optional.get());
    }
}
