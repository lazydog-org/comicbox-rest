package org.lazydog.comicbox.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.apache.commons.lang3.builder.CompareToBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * Entity class used to represent an imprint.
 *
 * @author rjrjr
 */
@Entity
@Table(name = "imprint", uniqueConstraints = @UniqueConstraint(columnNames = {"name", "publisher_id"}))
public class Imprint extends AbstractEntity<Imprint> {

    private static final long serialVersionUID = 1L;
    
    @NotNull(message = "Name is required.")
    @Size(max = 64, message = "Name cannot contain more than 64 characters.")
    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @JsonIgnore
    @Valid
    @NotNull(message = "Publisher is required.")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "publisher_id", nullable = false)
    private Publisher publisher;
    
    @Override
    public int compareTo(Imprint that) {
        return new CompareToBuilder()
                .append(this.getName(), that.getName())
                .toComparison();
    }

    /**
     * Get the name.
     *
     * @return the name.
     */
    public String getName() {
        return this.name;
    }

    /**
     * Get the publisher.
     *
     * @return the publisher.
     */
    public Publisher getPublisher() {
        return this.publisher;
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(61, 15)
                .append(this.getName())
                .toHashCode();
    }

    /**
     * Set the name.
     *
     * @param name the name.
     */
    public void setName(String name) {
        this.name = stripped(name);
    }

    /**
     * Set the publisher.
     *
     * @param publisher the publisher.
     */
    public void setPublisher(Publisher publisher) {
        this.publisher = publisher;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("name", this.getName())
                .toString();
    }
}
