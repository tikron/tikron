/**
 * Copyright (c) 2015 by Titus Kruse.
 */
package de.tikron.persistence.dao.user;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import de.tikron.persistence.model.gallery.Picture;
import de.tikron.persistence.model.misc.Clip;
import de.tikron.persistence.model.user.Rating;
import de.tikron.persistence.model.user.RatingResult;
import de.tikru.commons.jpa.dao.GenericJpaDao;
import de.tikru.commons.jpa.dao.JpaUtil;

/**
 * JPA implementation of {@link RatingDao}.
 *
 * @since 27.04.2015
 * @author Titus Kruse
 */
public class RatingDaoJpaImpl extends GenericJpaDao<Rating, Long> implements RatingDao {

	public RatingDaoJpaImpl() {
		super(Rating.class);
	}

	public RatingDaoJpaImpl(EntityManager entityManager) {
		super(Rating.class, entityManager);
	}

	@Override
	public RatingResult getResult(Picture picture) {
		String queryStr = "SELECT NEW de.tikron.persistence.model.user.RatingResult(r.ratingType.id, r.picture.id, COUNT(r), AVG(r.value))"
				+ " FROM Rating r WHERE r.picture = :picture GROUP BY r.ratingType, r.picture";
//		String queryStr = "SELECT NEW de.tikron.persistence.model.user.RatingResult(COUNT(r), AVG(r.value))"
//				+ " FROM Rating r WHERE r.picture = :picture";
		TypedQuery<RatingResult> query = entityManager.createQuery(queryStr, RatingResult.class);
		query.setParameter("picture", picture);
		return JpaUtil.singleResultOrNull(query);
	}

	@Override
	public RatingResult getResult(Clip clip) {
		String queryStr = "SELECT NEW de.tikron.persistence.model.user.RatingResult(r.ratingType.id, r.clip.id, COUNT(r), AVG(r.value))"
				+ " FROM Rating r WHERE r.clip = :clip GROUP BY r.ratingType, r.clip";
		TypedQuery<RatingResult> query = entityManager.createQuery(queryStr, RatingResult.class);
		query.setParameter("clip", clip);
		return JpaUtil.singleResultOrNull(query);
	}

	@Override
	public List<RatingResult> getClipResults() {
		String queryStr = "SELECT NEW de.tikron.persistence.model.user.RatingResult(r.ratingType.id, r.clip.id, COUNT(r), AVG(r.value))"
				+ " FROM Rating r WHERE r.ratingType.id = 'CLIP' GROUP BY r.ratingType, r.clip";
		TypedQuery<RatingResult> query = entityManager.createQuery(queryStr, RatingResult.class);
		return query.getResultList();
	}

}
