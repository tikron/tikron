/**
 * Copyright (c) 2015 by Titus Kruse.
 */
package de.tikron.persistence.dao.gallery;

import javax.persistence.EntityManager;

import de.tikron.jpa.dao.GenericJpaDao;
import de.tikron.persistence.model.gallery.CategoryType;
import de.tikron.persistence.model.gallery.CategoryTypeId;

/**
 * JPA implementation of {@link CategoryTypeDao}
 *
 * @date 05.04.2015
 * @author Titus Kruse
 */
public class CategoryTypeDaoJpaImpl extends GenericJpaDao<CategoryType, CategoryTypeId> implements CategoryTypeDao {

	public CategoryTypeDaoJpaImpl() {
		super(CategoryType.class);
	}

	public CategoryTypeDaoJpaImpl(EntityManager entityManager) {
		super(CategoryType.class, entityManager);
	}

}
