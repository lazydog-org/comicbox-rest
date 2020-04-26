package org.lazydog.comicbox.model;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.apache.commons.lang3.builder.CompareToBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * Entity class used to represent a title.
 *
 * @author rjrjr
 */
@Entity
@Table(name = "title", uniqueConstraints = @UniqueConstraint(columnNames = {"name", "volume"}))
public class Title extends AbstractEntity<Title> {

    private static final long serialVersionUID = 1L;

    @Valid
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "title_category",
            joinColumns = @JoinColumn(name = "title_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id"))
    private Set<Category> categories = new HashSet<>();

    @Valid
    @NotNull(message = "Duration is required.")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "duration_id", nullable = false)
    private Duration duration;

    @Valid
    @NotNull(message = "Format is required.")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "format_id", nullable = false)
    private Format format;
    
    @Valid
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "image_id", nullable = true)
    private Image image;

    @Valid
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "title_imprint",
            joinColumns = @JoinColumn(name = "title_id"),
            inverseJoinColumns = @JoinColumn(name = "imprint_id"))
    private Set<Imprint> imprints = new HashSet<>();

    @NotNull(message = "Name is required.")
    @Size(max = 128, message = "Name cannot contain more than 128 characters.")
    @Column(name = "name", nullable = false)
    private String name;
    
    @Column(name = "publish_end_date", nullable = true)
    private LocalDate publishEndDate;
    
    @Column(name = "publish_start_date", nullable = true)
    private LocalDate publishStartDate;
    
    @Valid
    @NotEmpty(message = "Publishers is required.")
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "title_publisher",
            joinColumns = @JoinColumn(name = "title_id"),
            inverseJoinColumns = @JoinColumn(name = "publisher_id"))
    private Set<Publisher> publishers = new HashSet<>();
    
    @NotNull(message = "Volume is required.")
    @Min(value = 1, message = "Volume must be at least 1.")
    @Max(value = 99, message = "Volume must be at most 99.")
    @Column(name = "volume", nullable = false)
    private Integer volume = 1;

    @Override
    public int compareTo(Title that) {
        return new CompareToBuilder()
                .append(this.getName(), that.getName())
                .append(this.getVolume(), that.getVolume())
                .toComparison();
    }

    /**
     * Get the categories.
     *
     * @return the categories.
     */
    public Set<Category> getCategories() {
        return this.categories;
    }

    /**
     * Get the duration.
     *
     * @return the duration.
     */
    public Duration getDuration() {
        return this.duration;
    }

    /**
     * Get the format.
     *
     * @return the format.
     */
    public Format getFormat() {
        return this.format;
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

    /**
     * Get the publish end date.
     *
     * @return the publish end date.
     */
    public LocalDate getPublishEndDate() {
        return this.publishEndDate;
    }

    /**
     * Get the publish start date.
     *
     * @return the publish start date.
     */
    public LocalDate getPublishStartDate() {
        return this.publishStartDate;
    }

    /**
     * Get the publishers.
     *
     * @return the publishers.
     */
    public Set<Publisher> getPublishers() {
        return this.publishers;
    }

    /**
     * Get the volume.
     *
     * @return the volume.
     */
    public Integer getVolume() {
        return this.volume;
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(61, 15)
                .append(this.getName())
                .append(this.getVolume())
                .toHashCode();
    }

    /**
     * Set the categories.
     *
     * @param categories the categories.
     */
    public void setCategories(Set<Category> categories) {
        this.categories = replaceSet(this.categories, categories);
    }

    /**
     * Set the duration.
     *
     * @param duration the duration.
     */
    public void setDuration(Duration duration) {
        this.duration = duration;
    }

    /**
     * Set the format.
     *
     * @param format the format.
     */
    public void setFormat(Format format) {
        this.format = format;
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

    /**
     * Set the publish end date.
     *
     * @param publishEndDate the publish end date.
     */
    public void setPublishEndDate(LocalDate publishEndDate) {
        this.publishEndDate = publishEndDate;
    }

    /**
     * Set the publish start date.
     *
     * @param publishStartDate the publish start date.
     */
    public void setPublishStartDate(LocalDate publishStartDate) {
        this.publishStartDate = publishStartDate;
    }

    /**
     * Set the publishers.
     *
     * @param publishers the publishers.
     */
    public void setPublishers(Set<Publisher> publishers) {
        this.publishers = replaceSet(this.publishers, publishers);
    }

    /**
     * Set the volume.
     *
     * @param volume the volume.
     */
    public void setVolume(Integer volume) {
        this.volume = (volume == null || volume < 1) ? 1 : volume;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("categories", this.getCategories())
                .append("duration", this.getDuration())
                .append("format", this.getFormat())
                .append("image", this.getImage())
                .append("name", this.getName())
                .append("publishEndDate", this.getPublishEndDate())
                .append("publishStartDate", this.getPublishStartDate())
                .append("publishers", this.getPublishers())
                .append("volume", this.getVolume())
                .toString();
    }
}
