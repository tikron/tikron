/**
 * Copyright (c) 2012 by Titus Kruse.
 */
package de.tikron.persistence.dao.user;

import de.tikron.jpa.dao.GenericDao;
import de.tikron.persistence.model.user.CommentType;
import de.tikron.persistence.model.user.CommentTypeId;

/**
 * DAO für Kommentartypen.
 *
 * @date 27.03.2012
 * @author Titus Kruse
 */
public interface CommentTypeDao extends GenericDao<CommentType, CommentTypeId> {

}
