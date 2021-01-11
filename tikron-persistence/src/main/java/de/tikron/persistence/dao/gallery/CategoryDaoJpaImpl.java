/**
 * Copyright (c) 2008 by Titus Kruse.
 */
package de.tikron.persistence.dao.gallery;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.hibernate.jpa.QueryHints;

import de.tikron.persistence.model.gallery.Catalog;
import de.tikron.persistence.model.gallery.Category;
import de.tikru.commons.jpa.dao.GenericJpaDao;
import de.tikru.commons.jpa.dao.JpaUtil;

/**
 * JPA implementation of {@link CategoryDao}.
 * 
 * @since 28.09.2008
 * @author Titus Kruse
 */
public class CategoryDaoJpaImpl extends GenericJpaDao<Category, Long> implements CategoryDao {

	public CategoryDaoJpaImpl() {
		super(Category.class);
	}

	public CategoryDaoJpaImpl(EntityManager entityManager) {
		super(Category.class, entityManager);
	}

	@Override
	public Category findByName(String name) {
		TypedQuery<Category> query = entityManager.createNamedQuery(Category.NQ_FIND_BY_NAME, Category.class);
		query.setParameter("name", name);
		return singleResultOrNull(query);
	}

	@Override
	public List<Category> findByVisibility(boolean visibleOnly) {
		TypedQuery<Category> query = entityManager.createNamedQuery(Category.NQ_FIND_BY_VISIBILITY, Category.class);
		query.setParameter("visibleOnly", visibleOnly);
		// Enable query caching for webapp top navigation
		query.setHint(QueryHints.HINT_CACHEABLE, Boolean.TRUE);
		return query.getResultList();
	}

	@Override
	public List<Category> findByCatalog(Catalog catalog) {
		TypedQuery<Category> query = entityManager.createNamedQuery(Category.NQ_FIND_BY_CATALOG, Category.class);
		query.setParameter("catalog", catalog);
		return query.getResultList();
	}

	public List<Category> findByCatalogOrderByName(Catalog catalog) {
		TypedQuery<Category> query = entityManager.createNamedQuery(Category.NQ_FIND_BY_CATALOG_ORDERBY_NAME,
				Category.class);
		query.setParameter("catalog", catalog);
		return query.getResultList();
	}

	@Override
	public List<Category> findByCatalogAndVisibility(Catalog catalog, boolean visibleOnly) {
		TypedQuery<Category> query = entityManager.createNamedQuery(Category.NQ_FIND_BY_CATALOG_AND_VISIBILITY,
				Category.class);
		query.setParameter("catalog", catalog);
		query.setParameter("visibleOnly", visibleOnly);
		// Enable query caching for webapp top navigation
		query.setHint(QueryHints.HINT_CACHEABLE, Boolean.TRUE);
		return query.getResultList();
	}

	@Override
	public List<Category> findByCatalogAndVisibilityOrderByName(Catalog catalog, boolean visibleOnly) {
		TypedQuery<Category> query = entityManager.createNamedQuery(Category.NQ_FIND_BY_CATALOG_AND_VISIBILITY_ORDERBY_NAME,
				Category.class);
		query.setParameter("catalog", catalog);
		query.setParameter("visibleOnly", visibleOnly);
		// Enable query caching for webapp top navigation
		query.setHint(QueryHints.HINT_CACHEABLE, Boolean.TRUE);
		return query.getResultList();
	}

	@Override
	public Category insert(Category entity) {
		if (JpaUtil.isLoaded(entityManager, entity.getCatalog().getCategories())) {
			entity.getCatalog().addCategory(entity);
		}
		return super.insert(entity);
	}

	@Override
	public void delete(Category entity) {
		if (JpaUtil.isLoaded(entityManager, entity.getCatalog().getCategories())) {
			entity.getCatalog().removeCategory(entity);
		}
		super.delete(entity);
	}

}
