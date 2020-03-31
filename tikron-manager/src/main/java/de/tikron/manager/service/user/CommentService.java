/**
 * Copyright (c) 2008 by Titus Kruse.
 */
package de.tikron.manager.service.user;

import java.util.List;

import de.tikron.manager.service.common.CRUDService;
import de.tikron.persistence.model.misc.Clip;
import de.tikron.persistence.model.user.ClipComment;
import de.tikron.persistence.model.user.Comment;

/**
 * Service für Kommentare.
 * 
 * @date 12.06.2008
 * @author Titus Kruse
 */
public interface CommentService extends CRUDService<Comment, Long> {

	/**
	 * Liste aller Kommentare eines Kurzfilms holen.
	 * 
	 * @param clip Der Kurzfilm.
	 * @return Die Liste der Kommentare.
	 */
	public List<ClipComment> getComments(Clip clip);

}
