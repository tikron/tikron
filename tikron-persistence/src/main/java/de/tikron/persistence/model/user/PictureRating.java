/**
 * Copyright (c) 2015 by Titus Kruse.
 */
package de.tikron.persistence.model.user;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

import de.tikron.persistence.model.gallery.Picture;

/**
 * A user rating for a picture.
 *
 * @date 26.04.2015
 * @author Titus Kruse
 */
@Entity
@DiscriminatorValue("PICTURE")
@NamedQueries({
		@NamedQuery(name = PictureRating.NQ_FIND_BY_PICTURE, query = "SELECT o FROM PictureRating o WHERE o.picture = :picture")
})
public class PictureRating extends Rating {

	private static final long serialVersionUID = 6933617428740639350L;

	public static final String NQ_FIND_BY_PICTURE = "Rating.findByPicture";

	@ManyToOne(fetch = FetchType.LAZY) 	// Typically the picture is not used for each rating in a list
	@JoinColumn(name = "picture_id")
	private Picture picture;

	public PictureRating() {
	}

	public PictureRating(Picture picture) {
		this.picture = picture;
	}

	public PictureRating(Picture picture, Double value) {
		super(value);
		this.picture = picture;
	}

	public Picture getPicture() {
		return picture;
	}

	public void setPicture(Picture picture) {
		this.picture = picture;
	}

	@Override
	public de.tikru.commons.jpa.domain.ShowableEntity<Long> getRelatedEntity() {
		return getPicture();
	}

}
