/**
 * Copyright (c) 2012 by Titus Kruse.
 */
package de.tikron.persistence.model.user;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * Ein Gästebucheintrag.
 *
 * @date 29.03.2012
 * @author Titus Kruse
 */
@Entity
@DiscriminatorValue("GUESTBOOK")
public class GuestbookComment extends Comment {

	public GuestbookComment() {
	}

	public GuestbookComment(User user) {
		super(user);
	}

	@Override
	public de.tikru.commons.jpa.domain.ShowableEntity<Long> getRelatedEntity() {
		return null;
	}

}
