/**
 * Copyright (c) 2008 by Titus Kruse.
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
import de.tikron.manager.service.gallery.CatalogService;
import de.tikron.persistence.model.gallery.Catalog;

/**
 * Backing Bean für die Liste der Kataloge.
 * 
 * @author Titus Kruse
 */
@ManagedBean
@ViewScoped
public class CatalogListBean extends AbstractSelectableListBean<Catalog, Long> implements Serializable {

	@ManagedProperty(value = "#{catalogService}")
	private transient CatalogService catalogService;

	/**
	 * Bean initialisieren.
	 */
	public void preRenderView(ComponentSystemEvent e) {
		if (getList() == null) {
			setList(catalogService.getAll());
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
		List<Catalog> selectedItems = getSelectedItems();
		if (selectedItems.isEmpty()) {
			Message.sendMessage(null, "de.tikron.manager.ERROR_NO_ENTRIES_SELECTED", new Object[] {});
			return null;
		}
		return "/pages/gallery/editCatalog.xhtml?catalogId=" + selectedItems.get(0).getId() + "&faces-redirect=true";
	}

	/**
	 * Einträge löschen.
	 * 
	 * @return Faces-Navigation.
	 */
	public String delete() {
		List<Catalog> selectedItems = getSelectedItems();
		if (selectedItems.isEmpty()) {
			Message.sendMessage(null, "de.tikron.manager.ERROR_NO_ENTRIES_SELECTED", new Object[] {});
			return null;
		}
		for (Catalog catalog : selectedItems) {
			catalogService.delete(catalog);
		}
		return "/pages/common/confirmation.xhtml";
	}

	public void setCatalogService(CatalogService catalogService) {
		this.catalogService = catalogService;
	}

}
