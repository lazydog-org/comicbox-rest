package org.lazydog.comicbox.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.apache.commons.lang3.builder.CompareToBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * Entity class used to represent a location.
 *
 * @author rjrjr
 */
@Entity
@Table(name = "location", uniqueConstraints = @UniqueConstraint(columnNames = {"name", "username"}))
public class Location extends AbstractEntity<Location> {

    private static final long serialVersionUID = 1L;

    @NotNull(message = "Name is required.")
    @Size(max = 32, message = "Name cannot contain more than 32 characters.")
    @Column(name = "name", nullable = false)
    private String name;
    
    @NotNull(message = "Username is required.")
    @Size(max = 32, message = "Username cannot contain more than 32 characters.")
    @Column(name = "username", nullable = false)
    private String username;

    @Override
    public int compareTo(Location that) {
        return new CompareToBuilder()
                .append(this.getUsername(), that.getUsername())
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
                .append("name", this.getName())
                .append("username", this.getUsername())
                .toString();
    }
}
