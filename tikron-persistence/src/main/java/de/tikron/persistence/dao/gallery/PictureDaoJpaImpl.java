/**
 * Copyright (c) 2008 by Titus Kruse.
 */
package de.tikron.persistence.dao.gallery;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.hibernate.jpa.QueryHints;

import de.tikron.persistence.model.gallery.Category;
import de.tikron.persistence.model.gallery.Picture;
import de.tikru.commons.jpa.dao.GenericJpaDao;
import de.tikru.commons.jpa.dao.JpaUtil;

/**
 * JPA implementation of {@link PitureDao}
 * 
 * @date 28.09.2008
 * @author Titus Kruse
 */
public class PictureDaoJpaImpl extends GenericJpaDao<Picture, Long> implements PictureDao {

	public PictureDaoJpaImpl() {
		super(Picture.class);
	}

	public PictureDaoJpaImpl(EntityManager entityManager) {
		super(Picture.class, entityManager);
	}

	@Override
	public Picture findByName(String name) {
		TypedQuery<Picture> query = entityManager.createNamedQuery(Picture.NQ_FIND_BY_NAME, Picture.class);
		query.setParameter("name", name);
		return singleResultOrNull(query);
	}
	
	@Override
	public List<Picture> findByCategoryOrderByName(Category category) {
		TypedQuery<Picture> query = entityManager.createNamedQuery(Picture.NQ_FIND_BY_CATEGORY_ORDERBY_NAME, Picture.class);
		query.setParameter("category", category);
		query.setHint(QueryHints.HINT_CACHEABLE, Boolean.TRUE);
		return query.getResultList();
	}

	@Override
	public Picture findFirst(Category category) {
		TypedQuery<Picture> query = entityManager.createQuery(
				"SELECT o FROM Picture o WHERE o.category = :category ORDER BY o.name ASC", Picture.class);
		query.setParameter("category", category);
		query.setMaxResults(1);
		return firstResult(query);
	}

	@Override
	public Picture findPrevious(Category category, Picture picture) {
		TypedQuery<Picture> query = entityManager.createQuery(
				"SELECT o FROM Picture o WHERE o.category = :category AND o.name < :name ORDER BY o.name DESC", Picture.class);
		query.setParameter("category", category);
		query.setParameter("name", picture.getName());
		query.setMaxResults(1);
		return firstResult(query);
	}

	@Override
	public Picture findNext(Category category, Picture picture) {
		TypedQuery<Picture> query = entityManager.createQuery(
				"SELECT o FROM Picture o WHERE o.category = :category AND o.name > :name ORDER BY o.name ASC", Picture.class);
		query.setParameter("category", category);
		query.setParameter("name", picture.getName());
		query.setMaxResults(1);
		return firstResult(query);
	}

	@Override
	public Picture findLast(Category category) {
		TypedQuery<Picture> query = entityManager.createQuery(
				"SELECT o FROM Picture o WHERE o.category = :category ORDER BY o.name DESC", Picture.class);
		query.setParameter("category", category);
		query.setMaxResults(1);
		return firstResult(query);
	}

	@Override
	public Picture findForTeaser() {
		TypedQuery<Picture> query = entityManager
				.createQuery(
						"SELECT p FROM Picture p WHERE p.category.id IN (SELECT c.id FROM Category c WHERE c.visible = TRUE AND c.onTeaser = TRUE) ORDER BY RAND()",
						Picture.class);
		query.setMaxResults(1);
		return firstResult(query);
	}

	@Override
	public Picture insert(Picture entity) {
		if (JpaUtil.isLoaded(entityManager, entity.getCategory().getPictures())) {
			entity.getCategory().addPicture(entity);
		}
		return super.insert(entity);
	}

	@Override
	public void delete(Picture entity) {
		if (JpaUtil.isLoaded(entityManager, entity.getCategory().getPictures())) {
			entity.getCategory().removePicture(entity);
		}
		super.delete(entity);
	}

}
