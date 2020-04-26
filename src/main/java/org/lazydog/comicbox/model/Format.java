package org.lazydog.comicbox.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.apache.commons.lang3.builder.CompareToBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * Entity class used to represent a format.
 *
 * @author rjrjr
 */
@Entity
@Table(name = "format")
public class Format extends AbstractEntity<Format> {

    private static final long serialVersionUID = 1L;

    @NotNull(message = "Name is required.")
    @Size(max = 32, message = "Name cannot contain more than 32 characters.")
    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @Override
    public int compareTo(Format that) {
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

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("name", this.getName())
                .toString();
    }
}
