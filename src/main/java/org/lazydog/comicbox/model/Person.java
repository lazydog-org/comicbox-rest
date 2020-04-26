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
 * Entity class used to represent a person.
 *
 * @author rjrjr
 */
@Entity
@Table(name = "person", uniqueConstraints = @UniqueConstraint(columnNames = {"first_name", "last_name"}))
public class Person extends AbstractEntity<Person> {

    private static final long serialVersionUID = 1L;

    @NotNull(message = "First name is required.")
    @Size(max = 32, message = "First name cannot contain more than 32 characters.")
    @Column(name = "first_name", nullable = false)
    private String firstName;
    
    @NotNull(message = "Last name is required.")
    @Size(max = 32, message = "Last name cannot contain more than 32 characters.")
    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Override
    public int compareTo(Person that) {
        return new CompareToBuilder()
                .append(this.getLastName(), that.getLastName())
                .append(this.getFirstName(), that.getFirstName())
                .toComparison();
    }

    /**
     * Get the first name.
     *
     * @return the first name.
     */
    public String getFirstName() {
        return this.firstName;
    }

    /**
     * Get the last name.
     *
     * @return the last name.
     */
    public String getLastName() {
        return this.lastName;
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(61, 15)
                .append(this.getLastName())
                .append(this.getFirstName())
                .toHashCode();
    }

    /**
     * Set the first name.
     *
     * @param firstName the first name.
     */
    public void setFirstName(String firstName) {
        this.firstName = stripped(firstName);
    }

    /**
     * Set the last name.
     *
     * @param lastName the last name.
     */
    public void setLastName(String lastName) {
        this.lastName = stripped(lastName);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("firstName", this.getFirstName())
                .append("lastName", this.getLastName())
                .toString();
    }
}
