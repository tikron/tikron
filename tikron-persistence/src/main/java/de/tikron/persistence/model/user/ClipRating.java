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

import de.tikron.persistence.model.misc.Clip;

/**
 * A user rating for a video clip.
 *
 * @date 26.04.2015
 * @author Titus Kruse
 */
@Entity
@DiscriminatorValue("CLIP")
@NamedQueries({
		@NamedQuery(name = ClipRating.NQ_FIND_BY_CLIP, query = "SELECT o FROM ClipRating o WHERE o.clip = :clip")
})
public class ClipRating extends Rating {

	public static final String NQ_FIND_BY_CLIP = "Rating.findByClip";

	@ManyToOne(fetch = FetchType.LAZY) 	// Typically the clip is not used for each rating in a list
	@JoinColumn(name = "clip_id")
	private Clip clip;

	public ClipRating() {
	}

	public ClipRating(Clip clip) {
		this.clip = clip;
	}

	public ClipRating(Clip clip, Double value) {
		super(value);
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
