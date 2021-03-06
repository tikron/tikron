/**
 * Copyright (c) 2015 by Titus Kruse.
 */
package de.tikron.persistence.model.user;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.ToStringBuilder;

import de.tikru.commons.jpa.domain.EnumeratedKeyEntity;

/**
 * A type of rating.
 *
 * @author Titus Kruse
 * @since 26.04.2015
 */
@Entity
@NamedQueries({ @NamedQuery(name = RatingType.NQ_FIND_ALL, query = "SELECT o FROM RatingType o"),
		@NamedQuery(name = RatingType.NQ_FIND_BY_ID, query = "SELECT o FROM RatingType o WHERE o.id = :id"), })
@Table(name = "rating_type")
public class RatingType extends EnumeratedKeyEntity<RatingTypeId> {

	private static final long serialVersionUID = -1958430668169745989L;
	
	public static final String NQ_FIND_ALL = "RatingType.findAll";
	public static final String NQ_FIND_BY_ID = "RatingType.findById";

	public RatingType() {
	}

	public RatingType(RatingTypeId id) {
		super(id);
	}

	@Column
	private String description;

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this).append("id", id).append("description", description).toString();
	}

}
