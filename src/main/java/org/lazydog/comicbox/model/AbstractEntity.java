package org.lazydog.comicbox.model;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;
import javax.persistence.Version;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import org.apache.commons.lang3.SerializationUtils;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

/**
 * Generic entity.
 *
 * @author rjrjr
 *
 * @param <T> the type of the entity.
 */
@MappedSuperclass
public abstract class AbstractEntity<T extends AbstractEntity<T>> implements Serializable, Comparable<T> {

    private static final long serialVersionUID = 1L;

    @CreationTimestamp
    @Column(name = "created_time", nullable = false)
    private LocalDateTime createdTime;

    @Id
    @GeneratedValue
    @Column(name = "id", nullable = false)
    private UUID id;

    @Transient
    private Class<T> type;
    
    @UpdateTimestamp
    @Column(name = "updated_time", nullable = false)
    private LocalDateTime updatedTime;
    
    @Version
    @Column(name = "version", nullable = false)
    private Long version;

    public AbstractEntity() {
        this.type = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }
    
    /**
     * Create a copy of this object.
     *
     * @return a copy of this object.
     */
    public T copy() {
        return (T) SerializationUtils.clone(this);
    }
        
    @Override
    public boolean equals(Object object) {
        if (!(this.type.isInstance(object))) {
            return false;
        }
        if (this == object) {
            return true;
        }
        if (this.compareTo((T) object) == 0) {
            return true;
        }
        return false;
    }

    /**
     * Get the created time.
     *
     * @return the created time.
     */
    public LocalDateTime getCreatedTime() {
        return this.createdTime;
    }

    /**
     * Get the ID.
     *
     * @return the ID.
     */
    public UUID getId() {
        return this.id;
    }

    /**
     * Get the updated time.
     *
     * @return the updated time.
     */
    public LocalDateTime getUpdatedTime() {
        return this.updatedTime;
    }

    /**
     * Get the version.
     *
     * @return the version.
     */
    public Long getVersion() {
        return this.version;
    }

    /**
     * Safely replace the elements of the original set with the elements of the
     * replacement set.  This method is safe for use by JPA entities.
     * 
     * @param <U>
     * @param original the original set.
     * @param replacement the replacement set.
     * 
     * @return the set.
     */
    protected static <U> Set<U> replaceSet(final Set<U> original, final Set<U> replacement) {
        original.clear();
        if (replacement != null) {
            original.addAll(replacement);
        }
        return original;
    }
    
    /**
     * Replace the original object with the replacement object if the original
     * object is null.
     *
     * @param <U>
     * @param <V>
     * @param original the original object.
     * @param replacement the replacement object.
     *
     * @return the original object if it is not null, otherwise the replacement
     * object.
     *
     * @throws IllegalArgumentException if the replacement object is null.
     */
    protected static <U, V extends U> U replaceNull(final U original, final V replacement) {
        if (replacement == null) {
            throw new IllegalArgumentException("The replacement object cannot be null.");
        }

        return (original == null) ? replacement : original;
    }

    /**
     * Set the created time.
     *
     * @param createdTime the created time.
     */
    public void setCreatedTime(LocalDateTime createdTime) {
        this.createdTime = createdTime;
    }

    /**
     * Set the ID.
     *
     * @param id the ID.
     */
    public void setId(UUID id) {
        this.id = id;
    }

    /**
     * Set the updated time.
     *
     * @param updatedTime the updated time.
     */
    public void setUpdatedTime(LocalDateTime updatedTime) {
        this.updatedTime = updatedTime;
    }

    /**
     * Set the version.
     *
     * @param version the version.
     */
    public void setVersion(Long version) {
        this.version = version;
    }

    /**
     * Get the stripped string.
     *
     * @param value the string to stripped.
     *
     * @return the trimmed string.
     */
    protected static String stripped(String value) {
        return (value != null) ? value.strip() : null;
    }

    /**
     * Validate this object.
     *
     * @return a set of constraint violations or an empty set if there are no
     * constraint violations.
     */
    @SuppressWarnings("unchecked")
    public Set<ConstraintViolation<T>> validate() {

        // Get the validator factory.
        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();

        // Get the validator for this object type.
        Validator validator = validatorFactory.getValidator();

        // Validate this object.
        Set<ConstraintViolation<T>> constraintViolations = validator.validate((T) this);

        return constraintViolations;
    }
}
