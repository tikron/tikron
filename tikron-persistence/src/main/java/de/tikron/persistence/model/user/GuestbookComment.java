/**
 * Copyright (c) 2012 by Titus Kruse.
 */
package de.tikron.persistence.model.user;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * Ein Gästebucheintrag.
 *
 * @author Titus Kruse
 * @since 29.03.2012
 */
@Entity
@DiscriminatorValue("GUESTBOOK")
public class GuestbookComment extends Comment {

	private static final long serialVersionUID = -4719908455109462544L;

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
