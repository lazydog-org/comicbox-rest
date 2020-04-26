package org.lazydog.comicbox.model;

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
 * Entity class used to represent an creator.
 *
 * @author rjrjr
 */
@Entity
@Table(name = "creator", uniqueConstraints = @UniqueConstraint(columnNames = {"person_id", "profession_id"}))
public class Creator extends AbstractEntity<Creator> {

    private static final long serialVersionUID = 1L;

    @Valid
    @NotNull(message = "Person is required.")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "person_id", nullable = false)
    private Person person;
    
    @Valid
    @NotNull(message = "Profession is required.")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "profession_id", nullable = false)
    private Profession profession;

    @Override
    public int compareTo(Creator that) {
        return new CompareToBuilder()
                .append(this.getPerson(), that.getPerson())
                .append(this.getProfession(), that.getProfession())
                .toComparison();
    }

    /**
     * Get the person.
     *
     * @return the person.
     */
    public Person getPerson() {
        return this.person;
    }

    /**
     * Get the profession.
     *
     * @return the profession.
     */
    public Profession getProfession() {
        return this.profession;
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(61, 15)
                .append(this.getPerson())
                .append(this.getProfession())
                .toHashCode();
    }

    /**
     * Set the person.
     *
     * @param person the person.
     */
    public void setPerson(Person person) {
        this.person = person;
    }

    /**
     * Set the profession.
     *
     * @param profession the profession.
     */
    public void setProfession(Profession profession) {
        this.profession = profession;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("person", this.getPerson())
                .append("profession", this.getProfession())
                .toString();
    }
}
