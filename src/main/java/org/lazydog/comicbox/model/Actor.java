package org.lazydog.comicbox.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.apache.commons.lang3.builder.CompareToBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * Entity class used to represent an actor.
 *
 * @author rjrjr
 */
@Entity
@Table(name = "actor", uniqueConstraints = @UniqueConstraint(columnNames = {"iteration", "name"}))
public class Actor extends AbstractEntity<Actor> {

    private static final long serialVersionUID = 1L;

    @Valid
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "image_id", nullable = true)
    private Image image;
    
    @NotNull(message = "Iteration is required.")
    @Min(value = 1, message = "Iteration must be at least 1.")
    @Max(value = 99, message = "Iteration must be at most 99.")
    @Column(name = "iteration", nullable = false)
    private Integer iteration;
    
    @NotNull(message = "Name is required.")
    @Size(max = 32, message = "Name cannot contain more than 32 characters.")
    @Column(name = "name", nullable = false)
    private String name;

    @Override
    public int compareTo(Actor that) {
        return new CompareToBuilder()
                .append(this.getName(), that.getName())
                .append(this.getIteration(), that.getIteration())
                .toComparison();
    }

    /**
     * Get the image.
     *
     * @return the image.
     */
    public Image getImage() {
        return this.image;
    }

    /**
     * Get the iteration.
     *
     * @return the iteration.
     */
    public Integer getIteration() {
        return this.iteration;
    }

    /**
     * Get the name.
     *
     * @return the name.
     */
    public String getName() {
        return this.name;
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(61, 15)
                .append(this.getName())
                .append(this.getIteration())
                .toHashCode();
    }

    /**
     * Set the image.
     *
     * @param image the image.
     */
    public void setImage(Image image) {
        this.image = image;
    }

    /**
     * Set the iteration.
     *
     * @param iteration the iteration.
     */
    public void setIteration(Integer iteration) {
        this.iteration = replaceNull(iteration, 1);
    }

    /**
     * Set the name.
     *
     * @param name the name.
     */
    public void setName(String name) {
        this.name = stripped(name);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("image", this.getImage())
                .append("iteration", this.getIteration())
                .append("name", this.getName())
                .toString();
    }
}
