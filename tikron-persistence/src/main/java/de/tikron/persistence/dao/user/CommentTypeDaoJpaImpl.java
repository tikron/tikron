/**
 * Copyright (c) 2012 by Titus Kruse.
 */
package de.tikron.persistence.dao.user;

import javax.persistence.EntityManager;

import de.tikron.persistence.model.user.CommentType;
import de.tikron.persistence.model.user.CommentTypeId;
import de.tikru.commons.jpa.dao.GenericJpaDao;

/**
 * DAO für Kommentartypen.
 *
 * @author Titus Kruse
 * @since 27.03.2012
 */
public class CommentTypeDaoJpaImpl extends GenericJpaDao<CommentType, CommentTypeId> implements CommentTypeDao {

	/**
	 * Data Access Object erstellen
	 */
	public CommentTypeDaoJpaImpl() {
		super(CommentType.class);
	}

	/**
	 * Data Access Object mit EntityManager erstellen
	 * 
	 * @param entityManager Setzt den EntityManager für dieses DAO.
	 */
	public CommentTypeDaoJpaImpl(EntityManager entityManager) {
		super(CommentType.class, entityManager);
	}

}
