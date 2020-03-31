/**
 * Copyright (c) 2012 by Titus Kruse.
 */
package de.tikron.manager.converter.gallery;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;

import de.tikron.manager.converter.common.NumericKeyEntityConverter;
import de.tikron.manager.service.gallery.CategoryService;
import de.tikron.persistence.model.gallery.Category;

/**
 * Converter used as an interface between primary key and entity.
 * 
 * @date 12.02.2012
 * @author Titus Kruse
 */
@ManagedBean
@ApplicationScoped
// Service won't be set in FacesConverter
// @FacesConverter(forClass=Category.class)
public class CategoryConverter extends NumericKeyEntityConverter<Category> {

	@ManagedProperty(value = "#{categoryService}")
	private CategoryService categoryService;

	public CategoryConverter() {
		super(Category.class);
	}

	public void setCategoryService(CategoryService categoryService) {
		super.setService(categoryService);
	}

}
