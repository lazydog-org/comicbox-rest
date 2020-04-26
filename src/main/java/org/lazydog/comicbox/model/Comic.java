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
import javax.persistence.OneToMany;
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
 * Entity class used to represent a comic.
 *
 * @author rjrjr
 */
@Entity
@Table(name = "comic", uniqueConstraints = @UniqueConstraint(columnNames = {"identifier", "title_id"}))
public class Comic extends AbstractEntity<Comic> {

    private static final long serialVersionUID = 1L;

    @Valid
    @OneToMany(fetch = FetchType.LAZY, mappedBy="comic")
    private Set<ComicActor> comicActors = new HashSet<>();
    
    @Column(name = "cover_price", nullable = true)
    private Float coverPrice = 0.0f;
    
    @Valid
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "comic_creator",
            joinColumns = @JoinColumn(name = "comic_id"),
            inverseJoinColumns = @JoinColumn(name = "creator_id"))
    private Set<Creator> creators = new HashSet<>();
    
    @Column(name = "description", nullable = true)
    @Size(max = 256, message = "Description cannot contain more than 256 characters.")
    private String description;
    
    @Valid
    @NotNull(message = "Distribution is required.")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "distribution_id", nullable = false)
    private Distribution distribution;
    
    @NotNull(message = "Identifier is required.")
    @Size(max = 16, message = "Identifier cannot contain more than 16 characters.")
    @Column(name = "identifier", nullable = false)
    private String identifier;
    
    @Valid
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "image_id", nullable = true)
    private Image image;
    
    @NotNull(message = "Print is required.")
    @Min(value = 1, message = "Print must be at least 1.")
    @Max(value = 99, message = "Print must be at most 99.")
    @Column(name = "print", nullable = false)
    private Integer print = 1;
    
    @Column(name = "publish_date", nullable = true)
    private LocalDate publishDate;
    
    @Valid
    @NotNull(message = "Title is required.")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "title_id", nullable = false)
    private Title title;

    @Override
    public int compareTo(Comic that) {
        return new CompareToBuilder()
                .append(this.getTitle(), that.getTitle())
                .append(this.getIdentifier(), that.getIdentifier())
                .toComparison();
    }

    /**
     * Get the comic actors.
     *
     * @return the comic actors.
     */
    public Set<ComicActor> getComicActors() {
        return this.comicActors;
    }

    /**
     * Get the cover price.
     *
     * @return the cover price.
     */
    public Float getCoverPrice() {
        return this.coverPrice;
    }

    /**
     * Get the creators.
     *
     * @return the creators.
     */
    public Set<Creator> getCreators() {
        return this.creators;
    }

    /**
     * Get the description.
     *
     * @return the description.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Get the distribution.
     *
     * @return the distribution.
     */
    public Distribution getDistribution() {
        return this.distribution;
    }

    public String getIdentifier() {
        return this.identifier;
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
     * Get the print.
     *
     * @return the print.
     */
    public Integer getPrint() {
        return this.print;
    }

    /**
     * Get the publish date.
     *
     * @return the publish date.
     */
    public LocalDate getPublishDate() {
        return this.publishDate;
    }

    /**
     * Get the title.
     *
     * @return the title.
     */
    public Title getTitle() {
        return this.title;
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(61, 15)
                .append(this.getTitle())
                .append(this.getIdentifier())
                .toHashCode();
    }

    /**
     * Set the comic actors.
     *
     * @param comicActors the comic actors.
     */
    public void setComicActors(Set<ComicActor> comicActors) {
        this.comicActors = replaceSet(this.comicActors, comicActors);
    }

    /**
     * Set the cover price.
     *
     * @param coverPrice the cover price.
     */
    public void setCoverPrice(Float coverPrice) {
        this.coverPrice = (coverPrice == null || coverPrice < 0.0f) ? 0.0f : coverPrice;
    }

    /**
     * Set the creators.
     *
     * @param creators the creators.
     */
    public void setCreators(Set<Creator> creators) {
        this.creators = replaceSet(this.creators, creators);
    }

    /**
     * Set the description.
     *
     * @param description the description.
     */
    public void setDescription(String description) {
        this.description = stripped(description);
    }

    /**
     * Set the distribution.
     *
     * @param distribution the distribution.
     */
    public void setDistribution(Distribution distribution) {
        this.distribution = distribution;
    }

    /**
     * Set the identifier.
     *
     * @param identifier the identifier.
     */
    public void setIdentifier(String identifier) {
        this.identifier = stripped(identifier);
    }

    /**
     * Set the print.
     *
     * @param print the print.
     */
    public void setPrint(Integer print) {
        this.print = (print == null || print < 1) ? 1 : print;
    }

    /**
     * Set the publish date.
     *
     * @param publishDate the publish date.
     */
    public void setPublishDate(LocalDate publishDate) {
        this.publishDate = publishDate;
    }

    /**
     * Set the title.
     *
     * @param title the title.
     */
    public void setTitle(Title title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("comicActors", this.getComicActors())
                .append("coverPrice", this.getCoverPrice())
                .append("creators", this.getCreators())
                .append("description", this.getDescription())
                .append("distribution", this.getDistribution())
                .append("identifier", this.getIdentifier())
                .append("image", this.getImage())
                .append("print", this.getPrint())
                .append("publishDate", this.getPublishDate())
                .append("title", this.getTitle())
                .toString();
    }
}
