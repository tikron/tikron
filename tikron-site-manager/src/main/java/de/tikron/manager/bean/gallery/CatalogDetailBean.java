/**
 * Copyright (c) 2008 by Titus Kruse.
 */
package de.tikron.manager.bean.gallery;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ComponentSystemEvent;

import de.tikron.manager.bean.common.AbstractDetailBean;
import de.tikron.manager.service.gallery.CatalogService;
import de.tikron.persistence.model.gallery.Catalog;

/**
 * Backing Bean für einzelnen Catalog.
 * 
 * @author Titus Kruse
 */
@ManagedBean
@ViewScoped
public class CatalogDetailBean extends AbstractDetailBean<Catalog> {

	private Catalog catalog;

	@ManagedProperty(value = "#{catalogService}")
	private CatalogService catalogService;

	public void preRenderView(ComponentSystemEvent e) {
		if (catalog == null) {
			catalog = new Catalog();
		}
	}

	/**
	 * Action-Method "Speichern"
	 * 
	 * @return Navigation-Case.
	 */
	public String save() {
		catalogService.save(catalog);
		return "/pages/common/confirmation.xhtml";
	}

	/**
	 * Action-Method "Löschen"
	 * 
	 * @return Navigation-Case.
	 */
	public String delete() {
		catalogService.delete(catalog);
		return "/pages/common/confirmation.xhtml";
	}

	public Catalog getCatalog() {
		return catalog;
	}

	public void setCatalog(Catalog catalog) {
		this.catalog = catalog;
	}

	public void setCatalogService(CatalogService catalogService) {
		this.catalogService = catalogService;
	}
}
