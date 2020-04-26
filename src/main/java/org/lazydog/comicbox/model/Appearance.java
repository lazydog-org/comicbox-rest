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
 * Entity class used to represent an appearance.
 *
 * @author rjrjr
 */
@Entity
@Table(name = "appearance")
public class Appearance extends AbstractEntity<Appearance> {

    private static final long serialVersionUID = 1L;
    
    @NotNull(message = "Classification is required.")
    @Size(max = 32, message = "Classification cannot contain more than 32 characters.")
    @Column(name = "classification", nullable = false)
    private String classification;

    @Override
    public int compareTo(Appearance that) {
        return new CompareToBuilder()
                .append(this.getClassification(), that.getClassification())
                .toComparison();
    }

    /**
     * Get the classification.
     *
     * @return the classification.
     */
    public String getClassification() {
        return this.classification;
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(61, 15)
                .append(this.getClassification())
                .toHashCode();
    }

    /**
     * Set the classification.
     *
     * @param classification the classification.
     */
    public void setClassification(String classification) {
        this.classification = stripped(classification);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("classification", this.getClassification())
                .toString();
    }
}
