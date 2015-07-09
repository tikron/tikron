/**
 * Copyright (c) 2012 by Titus Kruse.
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
 * Ein Bild-Kommentar.
 *
 * @date 29.03.2012
 * @author Titus Kruse
 */
@Entity
@DiscriminatorValue("PICTURE")
@NamedQueries({
		@NamedQuery(name = PictureComment.NQ_FIND_BY_PICTURE, query = "SELECT o FROM PictureComment o WHERE o.picture = :picture"),
		@NamedQuery(name = PictureComment.NQ_FIND_VISIBLE_BY_PICTURE, query = "SELECT o FROM PictureComment o WHERE o.picture = :picture AND o.visible = true") })
public class PictureComment extends Comment {

	public static final String NQ_FIND_BY_PICTURE = "Comment.findByPicture";
	public static final String NQ_FIND_VISIBLE_BY_PICTURE = "Comment.findVisibleByPicture";

	@ManyToOne(fetch = FetchType.LAZY) 	// Typically the picture is not used for each comment in a list
	@JoinColumn(name = "picture_id")
	private Picture picture;

	public PictureComment() {
	}

	public PictureComment(Picture picture, User user) {
		super(user);
		this.picture = picture;
	}

	public PictureComment(Picture picture, User user, String text) {
		super(user, text);
		this.picture = picture;
	}

	public Picture getPicture() {
		return picture;
	}

	public void setPicture(Picture picture) {
		this.picture = picture;
	}

	@Override
	public de.tikron.jpa.domain.ShowableEntity<Long> getRelatedEntity() {
		return getPicture();
	}


}
