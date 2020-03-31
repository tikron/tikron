/**
 * Copyright (c) 2012 by Titus Kruse.
 */
package de.tikron.persistence.model.user;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedAttributeNode;
import javax.persistence.NamedEntityGraph;
import javax.persistence.NamedEntityGraphs;
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
@NamedEntityGraphs({
	@NamedEntityGraph(name = PictureComment.NEG_PICTURE, attributeNodes={@NamedAttributeNode("picture")})
})
public class PictureComment extends Comment {

	private static final long serialVersionUID = -6384859053020186692L;
	
	public static final String NQ_FIND_BY_PICTURE = "Comment.findByPicture";
	public static final String NQ_FIND_VISIBLE_BY_PICTURE = "Comment.findVisibleByPicture";
	
	public static final String NEG_PICTURE = "Comment.includePicture";

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
	public de.tikru.commons.jpa.domain.ShowableEntity<Long> getRelatedEntity() {
		return getPicture();
	}


}
