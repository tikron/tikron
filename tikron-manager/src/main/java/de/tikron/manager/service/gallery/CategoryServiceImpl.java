/**
 * Copyright (c) 2012 by Titus Kruse.
 */
package de.tikron.manager.service.gallery;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import de.tikron.manager.service.common.CRUDServiceImpl;
import de.tikron.persistence.dao.gallery.CategoryDao;
import de.tikron.persistence.model.gallery.Catalog;
import de.tikron.persistence.model.gallery.Category;

/**
 * Default-Implementation des CategoryService.
 * 
 * @date 12.02.2012
 * @author Titus Kruse
 */
@Service("categoryService")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class CategoryServiceImpl extends CRUDServiceImpl<Category, Long> implements CategoryService {

	@Transactional(readOnly = true)
	public List<Category> getCategories(Catalog catalog) {
		return getCategoryDao().findByCatalog(catalog);
	}

	protected CategoryDao getCategoryDao() {
		return (CategoryDao) super.getDao();
	}

	@Autowired
	public void setCategoryDao(CategoryDao dao) {
		super.setDao(dao);
	}

}
