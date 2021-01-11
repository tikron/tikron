/**
 * Copyright (c) 2015 by Titus Kruse.
 */
package de.tikron.manager.service.gallery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import de.tikron.manager.service.common.CacheServiceImpl;
import de.tikron.persistence.dao.gallery.CategoryTypeDao;
import de.tikron.persistence.model.gallery.CategoryType;
import de.tikron.persistence.model.gallery.CategoryTypeId;

/**
 * Default implementation of {@link CategoryTypeService}.
 *
 * @since 05.04.2015
 * @author Titus Kruse
 */
@Service("categoryTypeService")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class CategoryTypeServiceImpl extends CacheServiceImpl<CategoryType, CategoryTypeId> implements CategoryTypeService {

	protected CategoryTypeDao getCategoryTypeDao() {
		return (CategoryTypeDao) super.getDao();
	}

	@Autowired
	public void setCategoryTypeDao(CategoryTypeDao dao) {
		super.setDao(dao);
	}

}
