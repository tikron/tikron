/**
 * Copyright (c) 2015 by Titus Kruse.
 */
package de.tikron.persistence.model.user;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.ToStringBuilder;

import de.tikru.commons.jpa.domain.GeneratedKeyEntity;

/**
 * A user rating of a publication.
 * 
 * @author Titus Kruse
 */
@Entity
@Inheritance
@DiscriminatorColumn(name = "ratingtype_id", columnDefinition = ("char(64)"))
@NamedQueries({
		@NamedQuery(name = Rating.NQ_FIND_ALL, query = "SELECT o FROM Rating o"),
		@NamedQuery(name = Rating.NQ_FIND_BY_RATINGTYPE, query = "SELECT o FROM Rating o WHERE o.ratingType = :ratingType")
})
@Table(name = "rating")
public abstract class Rating extends GeneratedKeyEntity<Long> {

	public static final String NQ_FIND_ALL = "Rating.findAll";
	public static final String NQ_FIND_BY_RATINGTYPE = "Rating.findByRatingType";

	@ManyToOne
	@JoinColumn(name = "ratingtype_id", insertable = false, updatable = false)
	private RatingType ratingType;

	@Column(nullable = false)
	private Double value;

	protected Rating() {
	}

	protected Rating(Double value) {
		this.value = value;
	}

	public RatingType getRatingType() {
		return ratingType;
	}
	
	public Double getValue() {
		return value;
	}

	public void setValue(Double value) {
		this.value = value;
	}

	/**
	 * Returns the related Entity of this rating.
	 * 
	 * @return The entity or null, if this type off rating hasn't any associated object.
	 */
	public abstract de.tikru.commons.jpa.domain.ShowableEntity<Long> getRelatedEntity();

	@Override
	public String toString() {
		return new ToStringBuilder(this).append("id", id).append("type", ratingType).append("createdOn", createdOn).append("value", value).toString();
	}

}
