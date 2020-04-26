package org.lazydog.comicbox.model;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.apache.commons.lang3.builder.CompareToBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * Entity class used to represent a publisher.
 *
 * @author rjrjr
 */
@Entity
@Table(name = "publisher", uniqueConstraints = @UniqueConstraint(columnNames = {"country_id", "name"}))
public class Publisher extends AbstractEntity<Publisher> {

    private static final long serialVersionUID = 1L;

    @Valid
    @NotNull(message = "Country is required.")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "country_id", nullable = false)
    private Country country;
    
    @Valid
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "image_id", nullable = true)
    private Image image;
    
    @Valid
    @OneToMany(fetch = FetchType.LAZY, mappedBy="publisher")
    private Set<Imprint> imprints = new HashSet<>();

    @NotNull(message = "Name is required.")
    @Size(max = 64, message = "Name cannot contain more than 64 characters.")
    @Column(name = "name", nullable = false)
    private String name;

    @Override
    public int compareTo(Publisher that) {
        return new CompareToBuilder()
                .append(this.getName(), that.getName())
                .append(this.getCountry(), that.getCountry())
                .toComparison();
    }

    /**
     * Get the country.
     *
     * @return the country.
     */
    public Country getCountry() {
        return this.country;
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
     * Get the imprints.
     *
     * @return the imprints.
     */
    public Set<Imprint> getImprints() {
        return this.imprints;
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
                .append(this.getCountry())
                .toHashCode();
    }

    /**
     * Set the country.
     *
     * @param country the country.
     */
    public void setCountry(Country country) {
        this.country = country;
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
     * Set the imprints.
     *
     * @param imprints the imprints.
     */
    public void setImprints(Set<Imprint> imprints) {
        this.imprints = replaceSet(this.imprints, imprints);
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
                .append("country", this.getCountry())
                .append("image", this.getImage())
                .append("name", this.getName())
                .toString();
    }
}
