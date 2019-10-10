/**
 * Copyright (c) 2015 by Titus Kruse.
 */
package de.tikron.persistence.dao.misc;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.hibernate.jpa.QueryHints;

import de.tikron.persistence.model.misc.WebRecommendation;
import de.tikru.commons.jpa.dao.GenericJpaDao;

/**
 * JPA implementation of web recommendation DAO.
 *
 * @date 21.03.2015
 * @author Titus Kruse
 */
public class WebRecommendationDaoJpaImpl extends GenericJpaDao<WebRecommendation, Long> implements WebRecommendationDao {
	
	public WebRecommendationDaoJpaImpl(EntityManager entityManager) {
		super(WebRecommendation.class, entityManager);
	}

	public WebRecommendationDaoJpaImpl() {
		super(WebRecommendation.class);
	}

	@Override
	public List<WebRecommendation> findAllOrderBySequence() {
		TypedQuery<WebRecommendation> query = entityManager.createNamedQuery(WebRecommendation.NQ_FIND_ALL_ORDERBY_SEQUENCE, WebRecommendation.class);
		query.setHint(QueryHints.HINT_CACHEABLE, Boolean.TRUE);
		return query.getResultList();
	}

}
