/**
 * Copyright (c) 2012 by Titus Kruse.
 */
package de.tikron.manager.bean.gallery;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ComponentSystemEvent;

import de.tikron.manager.bean.common.AbstractDetailBean;
import de.tikron.manager.service.gallery.CategoryService;
import de.tikron.persistence.model.gallery.Catalog;
import de.tikron.persistence.model.gallery.Category;

/**
 * Backing Bean für eine einzelne Kategorie.
 * 
 * @author Titus Kruse
 */
@ManagedBean
@ViewScoped
public class CategoryDetailBean extends AbstractDetailBean<Category> {

	private Category category;

	private Catalog catalog;

	@ManagedProperty(value = "#{categoryService}")
	private CategoryService categoryService;

	/**
	 * Bean initialisieren.
	 */
	public void preRenderView(ComponentSystemEvent e) {
		if (category == null) {
			category = new Category(catalog);
		}
	}

	/**
	 * Action-Method "Speichern"
	 * 
	 * @return Navigation-Case.
	 */
	public String save() {
		categoryService.save(category);
		return "/pages/common/confirmation.xhtml";
	}

	/**
	 * Action-Method "Löschen"
	 * 
	 * @return Navigation-Case.
	 */
	public String delete() {
		categoryService.delete(category);
		return "/pages/common/confirmation.xhtml";
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public Catalog getCatalog() {
		return catalog;
	}

	public void setCatalog(Catalog catalog) {
		this.catalog = catalog;
	}

	public void setCategoryService(CategoryService categoryService) {
		this.categoryService = categoryService;
	}
}
