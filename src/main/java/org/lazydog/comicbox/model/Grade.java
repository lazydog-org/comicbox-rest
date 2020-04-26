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
 * Entity class used to represent a comic grade.
 *
 * @author rjrjr
 */
@Entity
@Table(name = "grade")
public class Grade extends AbstractEntity<Grade> {

    private static final long serialVersionUID = 1L;

    @NotNull(message = "Code is required.")
    @Size(max = 5, message = "Code cannot contain more than 5 characters.")
    @Column(name = "code", nullable = false, unique = true)
    private String code;
    @NotNull(message = "Name is required.")
    @Size(max = 20, message = "Name cannot contain more than 20 characters.")
    @Column(name = "name", nullable = false, unique = true)
    private String name;
    @NotNull(message = "Scale is required.")
    @Column(name = "scale", nullable = false, unique = true)
    private Float scale = 0.0f;

    @Override
    public int compareTo(Grade that) {
        return new CompareToBuilder()
                .append(this.getScale(), that.getScale())
                .toComparison();
    }

    /**
     * Get the code.
     *
     * @return the code.
     */
    public String getCode() {
        return this.code;
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
     * Get the scale.
     *
     * @return the scale.
     */
    public Float getScale() {
        return this.scale;
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(61, 15)
                .append(this.getName())
                .toHashCode();
    }

    /**
     * Set the code.
     *
     * @param code the code.
     */
    public void setCode(String code) {
        this.code = stripped(code);
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
     * Set the scale.
     *
     * @param scale the scale.
     */
    public void setScale(Float scale) {
        this.scale = replaceNull(scale, 0.0f);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("code", this.getCode())
                .append("name", this.getName())
                .append("scale", this.getScale())
                .toString();
    }
}
