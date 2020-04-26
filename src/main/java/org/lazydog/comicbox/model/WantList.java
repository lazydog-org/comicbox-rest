package org.lazydog.comicbox.model;

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
 * Entity class used to represent a want list.
 *
 * @author rjrjr
 */
@Entity
@Table(name = "want_list", uniqueConstraints = @UniqueConstraint(columnNames = {"comic_id", "username"}))
public class WantList extends AbstractEntity<WantList> {

    private static final long serialVersionUID = 1L;

    @Valid
    @NotNull(message = "Comic is required.")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "comic_id", nullable = false)
    private Comic comic;
    
    @NotNull(message = "Username is required.")
    @Size(max = 32, message = "Username cannot contain more than 32 characters.")
    @Column(name = "username", nullable = false)
    private String username;

    @Override
    public int compareTo(WantList that) {
        return new CompareToBuilder()
                .append(this.getUsername(), that.getUsername())
                .append(this.getComic(), that.getComic())
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
                .append("username", this.getUsername())
                .toString();
    }
}
