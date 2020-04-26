package org.lazydog.comicbox.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.apache.commons.lang3.builder.CompareToBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * Entity class used to represent an inventory.
 *
 * @author rjrjr
 */
@Entity
@Table(name = "inventory", uniqueConstraints = @UniqueConstraint(columnNames = {"comic_id", "grade_id", "location_id", "username"}))
public class Inventory extends AbstractEntity<Inventory> {

    private static final long serialVersionUID = 1L;

    @Valid
    @NotNull(message = "Comic is required.")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "comic_id", nullable = false)
    private Comic comic;
    
    @Valid
    @NotNull(message = "Grade is required.")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "grade_id", nullable = false)
    private Grade grade;
    
    @Valid
    @NotNull(message = "Location is required.")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "location_id", nullable = false)
    private Location location;
    
    @Column(name = "purchase_price", nullable = true)
    private Float purchasePrice = 0.0f;
    
    @NotNull(message = "Quantity is required.")
    @Min(value = 1, message = "Quantity must be at least 1.")
    @Min(value = 999, message = "Quantity must be at most 999.")
    @Column(name = "quantity", nullable = false)
    private Integer quantity = 1;
    
    @NotNull(message = "Username is required.")
    @Size(max = 32, message = "Username cannot contain more than 32 characters.")
    @Column(name = "username", nullable = false)
    private String username;

    @Override
    public int compareTo(Inventory that) {
        return new CompareToBuilder()
                .append(this.getUsername(), that.getUsername())
                .append(this.getComic(), that.getComic())
                .append(this.getGrade(), that.getGrade())
                .append(this.getLocation(), that.getLocation())
                .toComparison();
    }

    /**
     * Get the comic.
     *
     * @return the comic.
     */
    public Comic getComic() {
        return this.comic;
    }

    /**
     * Get the comic grade.
     *
     * @return the comic grade.
     */
    public Grade getGrade() {
        return this.grade;
    }

    /**
     * Get the location.
     *
     * @return the location.
     */
    public Location getLocation() {
        return this.location;
    }

    /**
     * Get the purchase price.
     *
     * @return the purchase price.
     */
    public Float getPurchasePrice() {
        return this.purchasePrice;
    }

    /**
     * Get the quantity.
     *
     * @return the quantity.
     */
    public Integer getQuantity() {
        return this.quantity;
    }

    /**
     * Get the username.
     *
     * @return the username.
     */
    public String getUsername() {
        return this.username;
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(61, 15)
                .append(this.getUsername())
                .append(this.getComic())
                .append(this.getGrade())
                .append(this.getLocation())
                .toHashCode();
    }

    /**
     * Set the comic.
     *
     * @param comic the comic.
     */
    public void setComic(Comic comic) {
        this.comic = comic;
    }

    /**
     * Set the grade.
     *
     * @param grade the grade.
     */
    public void setGrade(Grade grade) {
        this.grade = grade;
    }

    /**
     * Set the location.
     *
     * @param location the location.
     */
    public void setLocation(Location location) {
        this.location = location;
    }

    /**
     * Set the purchase price.
     *
     * @param purchasePrice the purchase price.
     */
    public void setPurchasePrice(Float purchasePrice) {
        this.purchasePrice = (purchasePrice == null || purchasePrice < 0.0f) ? 0.0f : purchasePrice;
    }

    /**
     * Set the quantity.
     *
     * @param quantity the quantity.
     */
    public void setQuantity(Integer quantity) {
        this.quantity = (quantity == null || quantity < 1) ? 1 : quantity;
    }

    /**
     * Set the username.
     *
     * @param username the username.
     */
    public void setUsername(String username) {
        this.username = stripped(username);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("comic", this.getComic())
                .append("grade", this.getGrade())
                .append("location", this.getLocation())
                .append("purchasePrice", this.getPurchasePrice())
                .append("quantity", this.getQuantity())
                .append("username", this.getUsername())
                .toString();
    }
}
