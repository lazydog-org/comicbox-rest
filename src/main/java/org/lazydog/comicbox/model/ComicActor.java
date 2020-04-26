package org.lazydog.comicbox.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import org.apache.commons.lang3.builder.CompareToBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * Entity class used to represent a comic actor.
 *
 * @author rjrjr
 */
@Entity
@Table(name = "comic_actor", uniqueConstraints = @UniqueConstraint(columnNames = {"actor_id", "appearance_id", "comic_id"}))
public class ComicActor extends AbstractEntity<ComicActor> {

    private static final long serialVersionUID = 1L;

    @Valid
    @NotNull(message = "Actor is required.")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "actor_id", nullable = false)
    private Actor actor;
    
    @Valid
    @NotNull(message = "Appearance is required.")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "appearance_id", nullable = false)
    private Appearance appearance;
    
    @JsonIgnore
    @Valid
    @NotNull(message = "Comic is required.")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "comic_id", nullable = false)
    private Comic comic;

    @Override
    public int compareTo(ComicActor that) {
        return new CompareToBuilder()
                .append(this.getComic(), that.getComic())
                .append(this.getActor(), that.getActor())
                .append(this.getAppearance(), that.getAppearance())
                .toComparison();
    }

    /**
     * Get the actor.
     *
     * @return the actor.
     */
    public Actor getActor() {
        return this.actor;
    }

    /**
     * Get the appearance.
     *
     * @return the appearance.
     */
    public Appearance getAppearance() {
        return this.appearance;
    }

    /**
     * Get the comic.
     *
     * @return the comic.
     */
    public Comic getComic() {
        return this.comic;
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(61, 15)
                .append(this.getComic())
                .append(this.getActor())
                .append(this.getAppearance())
                .toHashCode();
    }

    /**
     * Set the actor.
     *
     * @param actor the actor.
     */
    public void setActor(Actor actor) {
        this.actor = actor;
    }

    /**
     * Set the appearance.
     *
     * @param appearance the appearance.
     */
    public void setAppearance(Appearance appearance) {
        this.appearance = appearance;
    }

    /**
     * Set the comic.
     *
     * @param comic the comic.
     */
    public void setComic(Comic comic) {
        this.comic = comic;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("actor", this.getActor())
                .append("appearance", this.getAppearance())
                .append("comic", this.getComic())
                .toString();
    }
}
