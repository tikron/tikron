/**
 * Copyright (c) 2015 by Titus Kruse.
 */
package de.tikron.manager.converter.gallery;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;

import de.tikron.manager.converter.common.AbstractEntityConverter;
import de.tikron.manager.service.gallery.CategoryTypeService;
import de.tikron.persistence.model.gallery.CategoryType;
import de.tikron.persistence.model.gallery.CategoryTypeId;

/**
 * Converter for type {@link de.tikron.persistence.model.gallery.CategoryTypeId}
 * 
 * @since 07.04.2015
 * @author Titus Kruse
 */
@ManagedBean
@ApplicationScoped
// Service won't be set in FacesConverter
// @FacesConverter(forClass=CategoryType.class)
public class CategoryTypeConverter extends AbstractEntityConverter<CategoryType, CategoryTypeId> {

	@ManagedProperty(value = "#{categoryTypeService}")
	private CategoryTypeService categoryTypeService;

	public CategoryTypeConverter() {
		super(CategoryType.class);
	}

	@Override
	protected CategoryTypeId toKey(String value) {
		return CategoryTypeId.valueOf(value);
	}

	public void setCategoryTypeService(CategoryTypeService categoryTypeService) {
		super.setService(categoryTypeService);
	}

}
