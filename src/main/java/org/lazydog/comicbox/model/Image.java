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
 * Entity class used to represent an image.
 *
 * @author rjrjr
 */
@Entity
@Table(name = "image")
public class Image extends AbstractEntity<Image> {

    private static final long serialVersionUID = 1L;

    @NotNull(message = "Filename is required.")
    @Size(max = 128, message = "Filename cannot contain more than 128 character.")
    @Column(name = "filename", nullable = false, unique = true)
    private String filename;

    @Override
    public int compareTo(Image that) {
        return new CompareToBuilder()
                .append(this.getFilename(), that.getFilename())
                .toComparison();
    }

    /**
     * Get the filename.
     *
     * @return the filename.
     */
    public String getFilename() {
        return this.filename;
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(61, 15)
                .append(this.getFilename())
                .toHashCode();
    }

    /**
     * Set the filename.
     *
     * @param filename the filename.
     */
    public void setFilename(String filename) {
        this.filename = stripped(filename);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("filename", this.getFilename())
                .toString();
    }
}
