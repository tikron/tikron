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

import de.tikron.persistence.model.misc.Clip;

/**
 * Ein Clip-Kommentar.
 *
 * @date 29.03.2012
 * @author Titus Kruse
 */
@Entity
@DiscriminatorValue("CLIP")
@NamedQueries({
		@NamedQuery(name = ClipComment.NQ_FIND_BY_CLIP, query = "SELECT o FROM ClipComment o WHERE o.clip = :clip"),
		@NamedQuery(name = ClipComment.NQ_FIND_VISIBLE_BY_CLIP, query = "SELECT o FROM ClipComment o WHERE o.clip = :clip AND o.visible = true") })
@NamedEntityGraphs({
	@NamedEntityGraph(name = ClipComment.NEG_CLIP, attributeNodes={@NamedAttributeNode("clip")})
})
public class ClipComment extends Comment {

	public static final String NQ_FIND_BY_CLIP = "Comment.findByClip";
	public static final String NQ_FIND_VISIBLE_BY_CLIP = "Comment.findVisibleByClip";
	
	public static final String NEG_CLIP = "Comment.includeClip";

	@ManyToOne(fetch = FetchType.LAZY) 	// Typically the clip is not used for each comment in a list
	@JoinColumn(name = "clip_id")
	private Clip clip;

	public ClipComment() {
	}

	public ClipComment(Clip clip, User user) {
		super(user);
		this.clip = clip;
	}

	public Clip getClip() {
		return clip;
	}

	public void setClip(Clip clip) {
		this.clip = clip;
	}

	@Override
	public de.tikru.commons.jpa.domain.ShowableEntity<Long> getRelatedEntity() {
		return getClip();
	}

}
