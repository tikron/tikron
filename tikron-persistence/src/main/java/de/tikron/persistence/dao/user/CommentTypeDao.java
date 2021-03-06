/**
 * Copyright (c) 2012 by Titus Kruse.
 */
package de.tikron.persistence.dao.user;

import de.tikron.persistence.model.user.CommentType;
import de.tikron.persistence.model.user.CommentTypeId;
import de.tikru.commons.jpa.dao.GenericDao;

/**
 * DAO für Kommentartypen.
 *
 * @author Titus Kruse
 * @since 27.03.2012
 */
public interface CommentTypeDao extends GenericDao<CommentType, CommentTypeId> {

}
