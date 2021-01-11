/**
 * Copyright (c) 2015 by Titus Kruse.
 */
package de.tikron.persistence.dao.misc;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.hibernate.jpa.QueryHints;

import de.tikron.persistence.model.misc.Clip;
import de.tikru.commons.jpa.dao.GenericJpaDao;

/**
 * JPA implementation of clip DAO.
 *
 * @author Titus Kruse
 * @since 14.03.2015
 */
public class ClipDaoJpaImpl extends GenericJpaDao<Clip, Long> implements ClipDao {
	
	public ClipDaoJpaImpl(EntityManager entityManager) {
		super(Clip.class, entityManager);
	}

	public ClipDaoJpaImpl() {
		super(Clip.class);
	}

	@Override
	public Clip findByName(String name) {
		TypedQuery<Clip> query = entityManager.createNamedQuery(Clip.NQ_FIND_BY_NAME, Clip.class);
		query.setParameter("name", name);
		return singleResultOrNull(query);
	}

	@Override
	public List<Clip> findAllOrderByDateRecorded() {
		TypedQuery<Clip> query = entityManager.createNamedQuery(Clip.NQ_FIND_ALL_ORDERBY_DATERECORDED, Clip.class);
		query.setHint(QueryHints.HINT_CACHEABLE, Boolean.TRUE);
		return query.getResultList();
	}

}
