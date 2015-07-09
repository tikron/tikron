/**
 * Copyright (c) 2012 by Titus Kruse.
 */
package de.tikron.manager.bean.gallery;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ComponentSystemEvent;

import de.tikron.faces.util.Message;
import de.tikron.manager.bean.common.AbstractSelectableListBean;
import de.tikron.manager.service.gallery.CategoryService;
import de.tikron.persistence.model.gallery.Catalog;
import de.tikron.persistence.model.gallery.Category;

/**
 * Backing Bean für eine Liste von Kategorien.
 * 
 * @author Titus Kruse
 */
@ManagedBean
@ViewScoped
public class CategoryListBean extends AbstractSelectableListBean<Category, Long> implements Serializable {

	private Catalog catalog;

	@ManagedProperty(value = "#{categoryService}")
	private CategoryService categoryService;

	/**
	 * Bean initialisieren.
	 */
	public void preRenderView(ComponentSystemEvent e) {
		if (getList() == null) {
			setList(categoryService.getCategories(catalog));
		}
	}

	/**
	 * Anzeige aktualisieren
	 * 
	 * @return Faces-Navigation.
	 */
	public String refresh() {
		setList(null);
		selectNone();
		return null;
	}

	/**
	 * Eintrag editieren.
	 * 
	 * @return Faces-Navigation.
	 */
	public String edit() {
		List<Category> selectedItems = getSelectedItems();
		if (selectedItems.isEmpty()) {
			Message.sendMessage(null, "de.tikron.manager.ERROR_NO_ENTRIES_SELECTED", new Object[] {});
			return null;
		}
		return "/pages/gallery/editCategory.xhtml?categoryId=" + selectedItems.get(0).getId() + "&faces-redirect=true";
	}

	/**
	 * Einträge löschen.
	 * 
	 * @return Faces-Navigation.
	 */
	public String delete() {
		List<Category> selectedItems = getSelectedItems();
		if (selectedItems.isEmpty()) {
			Message.sendMessage(null, "de.tikron.manager.ERROR_NO_ENTRIES_SELECTED", new Object[] {});
			return null;
		}
		for (Category category : selectedItems) {
			categoryService.delete(category);
		}
		return "/pages/common/confirmation.xhtml";
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
